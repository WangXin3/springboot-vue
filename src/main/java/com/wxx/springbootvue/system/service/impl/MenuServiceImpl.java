package com.wxx.springbootvue.system.service.impl;

import com.wxx.springbootvue.system.domain.dto.MenuDTO;
import com.wxx.springbootvue.system.domain.po.Menu;
import com.wxx.springbootvue.system.domain.vo.MenuMetaVO;
import com.wxx.springbootvue.system.domain.vo.MenuVO;
import com.wxx.springbootvue.system.mapper.MenuMapper;
import com.wxx.springbootvue.system.service.MenuService;
import com.wxx.springbootvue.system.util.JwtUser;
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
		List<MenuVO> content = buildMenu((List<MenuDTO>) map.get("content"));

		return content;
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
	public Map<String, Object> buildTree(List<MenuDTO> menuDTOS) {

		List<MenuDTO> trees = new ArrayList<>();
		Set<Long> ids = new HashSet<>();
		for (MenuDTO menuDTO : menuDTOS) {
			if (menuDTO.getParentId() == 0) {
				trees.add(menuDTO);
			}
			for (MenuDTO it : menuDTOS) {
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
			trees = menuDTOS.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
		}
		map.put("content", trees);
		map.put("totalElements", menuDTOS.size());
		return map;
	}

	@Override
	public List<MenuVO> buildMenu(List<MenuDTO> menuDTOS) {
		List<MenuVO> list = new LinkedList<>();
		menuDTOS.forEach(menuDTO -> {
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
					// 处理是一级菜单并且没有子菜单的情况
				} else if (menuDTO.getParentId() == 0) {
					menuVo.setChildren(null);
				}
				list.add(menuVo);
			}
		});

		return list;
	}


}
