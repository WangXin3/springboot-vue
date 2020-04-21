package com.wxx.springbootvue.system.service.impl;

import com.wxx.springbootvue.system.domain.dto.MenuDTO;
import com.wxx.springbootvue.system.domain.po.Menu;
import com.wxx.springbootvue.system.domain.vo.MenuMetaVO;
import com.wxx.springbootvue.system.domain.vo.MenuVO;
import com.wxx.springbootvue.system.mapper.MenuMapper;
import com.wxx.springbootvue.system.service.MenuService;
import com.wxx.springbootvue.system.util.JwtUser;
import com.wxx.springbootvue.utils.ObjectUtils;
import com.wxx.springbootvue.utils.RespBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 她爱微笑
 * @date 2020/3/24
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVO> findByUser() {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MenuDTO> menus = menuMapper.menuByUserId(user.getId());
        Map<String, Object> map = buildTree(menus);
        List<MenuDTO> content = ObjectUtils.castList(map.get("content"), MenuDTO.class);
        return buildMenu(content);
    }

    @Override
    public List<MenuDTO> findByPid(Long pid) {
        return menuMapper.findByPid(pid);
    }

    @Override
    public List findTreeList(List<MenuDTO> menus) {
        List<Map<String, Object>> list = new LinkedList<>();

        menus.forEach(menu -> {
            if (menu != null) {
                List<MenuDTO> menuList = menuMapper.findByPid(menu.getId());
                Map<String, Object> map = new HashMap<>(16);
                map.put("id", menu.getId());
                map.put("label", menu.getName());
                if (!CollectionUtils.isEmpty(menuList)) {
                    map.put("children", findTreeList(menuList));
                }

                list.add(map);
            }
        });


        return list;
    }

    @Override
    public Map<String, Object> buildTree(List<MenuDTO> menus) {

        List<MenuDTO> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuDTO menuDTO : menus) {
            if (menuDTO.getParentId() == 0) {
                trees.add(menuDTO);
            }
            for (MenuDTO it : menus) {
                if (it.getParentId().equals(menuDTO.getId())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        Map<String, Object> map = new HashMap<>(2);
        if (trees.size() == 0) {
            trees = menus.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        map.put("content", trees);
        map.put("totalElements", menus.size());
        return map;
    }

    @Override
    public List<MenuVO> buildMenu(List<MenuDTO> menus) {
        List<MenuVO> list = new LinkedList<>();
        menus.forEach(menuDTO -> {
            if (menuDTO != null) {
                List<MenuDTO> menuDtoList = menuDTO.getChildren();
                MenuVO menuVo = new MenuVO();
                menuVo.setName(StringUtils.isNotBlank(menuDTO.getComponentName()) ? menuDTO.getComponentName() : menuDTO.getName());
                // 一级目录需要加斜杠，不然会报警告
                menuVo.setPath(menuDTO.getParentId() == 0 ? "/" + menuDTO.getPath() : menuDTO.getPath());
                if (menuDTO.getParentId() == 0) {
                    menuVo.setComponent(StringUtils.isBlank(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                } else if (StringUtils.isNotBlank(menuDTO.getComponent())) {
                    menuVo.setComponent(menuDTO.getComponent());
                }

                menuVo.setMeta(new MenuMetaVO(menuDTO.getName(), menuDTO.getIcon()));
                if (menuDtoList != null && menuDtoList.size() != 0) {
                    menuVo.setChildren(buildMenu(menuDtoList));
                    // 处理没有子菜单的情况
                } else if (menuDTO.getParentId() == 0){
                    menuVo.setChildren(new ArrayList<>());

                    MenuVO menuVo1 = new MenuVO();
                    menuVo1.setMeta(menuVo.getMeta());

                    menuVo1.setPath("index");
                    menuVo1.setName(menuVo.getName());
                    menuVo1.setComponent(menuVo.getComponent());
                    menuVo.setName(null);
                    menuVo.setMeta(null);
                    menuVo.setComponent("Layout");
                    List<MenuVO> list1 = new ArrayList<>();
                    list1.add(menuVo1);
                    menuVo.setChildren(list1);
                } else {
                    menuVo.setChildren(new ArrayList<>());
                }
                list.add(menuVo);
            }
        });

        return list;
    }

    @Override
    public RespBean addMenu(Menu menu) {
        MenuDTO menuDTO = menuMapper.findByName(menu.getName(), menu.getComponentName());
        if (menuDTO != null) {
            return RespBean.error("菜单或者组件已存在");
        }

        int result = menuMapper.insertSelective(menu);
        if (result == 1) {
            return RespBean.success("新增成功");
        }

        return RespBean.error("新增失败");
    }

    @Override
    public RespBean editMenu(Menu menu) {
        MenuDTO menuDTO = menuMapper.findByName(menu.getName(), menu.getComponentName());
        if (menuDTO != null) {
            return RespBean.error("菜单或者组件已存在");
        }

        int result = menuMapper.updateByPrimaryKeySelective(menu);
        if (result == 1) {
            return RespBean.success("修改成功");
        }

        return RespBean.error("修改失败");
    }

    @Override
    public RespBean delMenu(Menu menu) {
        List<MenuDTO> children = menuMapper.findByPid(menu.getId());
        children.forEach(menuDTO -> {
            List<MenuDTO> byPid = menuMapper.findByPid(menuDTO.getId());
            if (byPid != null && byPid.size() != 0) {
                byPid.forEach(this::delMenu);
            }
        });

        return RespBean.success("删除成功");
    }


}
