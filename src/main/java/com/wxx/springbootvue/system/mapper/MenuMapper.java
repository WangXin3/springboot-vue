package com.wxx.springbootvue.system.mapper;

import com.wxx.springbootvue.system.domain.dto.MenuDTO;
import com.wxx.springbootvue.system.domain.po.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/24
 */
public interface MenuMapper {

    /**
     * 获取当前用户可以访问的菜单
     *
     * @param id -
     * @return -
     */
    List<MenuDTO> menuByUserId(Long id);

    /**
     * 根据父菜单id查询子菜单
     * @param pid -
     * @return -
     */
    List<MenuDTO> findByPid(Long pid);

    /**
     * 查询所有一级菜单
     * @return /
     */
    List<MenuDTO> findAllTopMenu();

    /**
     * 根据菜单名称查找菜单
     * @param name 菜单名称
     * @param componentName 组件名称
     * @return -
     */
    MenuDTO findByName(@Param("name") String name, @Param("componentName") String componentName);

    /**
     * 根据主键删除菜单
     * @param id /
     * @return /
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增菜单
     * @param record /
     * @return /
     */
    int insert(Menu record);

    /**
     * 新增菜单空值判断
     * @param record /
     * @return /
     */
    int insertSelective(Menu record);

    /**
     * 根据主键查询菜单
     * @param id /
     * @return /
     */
    Menu selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新菜单
     * @param record /
     * @return /
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * 根据主键更新菜单空值判断
     * @param record /
     * @return /
     */
    int updateByPrimaryKey(Menu record);

    /**
     * 根据角色id查询菜单
     * @param roleId 角色id
     * @return /
     */
    List<MenuDTO> findByRoleId(Long roleId);
}
