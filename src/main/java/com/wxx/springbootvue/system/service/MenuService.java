package com.wxx.springbootvue.system.service;

import com.wxx.springbootvue.system.domain.dto.MenuDTO;
import com.wxx.springbootvue.system.domain.vo.MenuVO;

import java.util.List;
import java.util.Map;

/**
 * @author 她爱微笑
 * @date 2020/3/24
 */
public interface MenuService {

	/**
	 * 获取当前用户可以访问的菜单
	 *
	 * @return
	 */
	List<MenuVO> findByUser();

	/**
	 * 根据父菜单id 查询子菜单
	 *
	 * @param pid
	 * @return
	 */
	List<MenuDTO> findByPid(Long pid);

	/**
	 * 查询所有的菜单组装成树结构
	 *
	 * @param menus
	 * @return
	 */
	List findTreeList(List<MenuDTO> menus);

	/**
	 * 构建菜单树
	 * @param menuDTOS
	 * @return
	 */
	Map<String, Object> buildTree(List<MenuDTO> menuDTOS);


	/**
	 * 构建前端路由所需的菜单结构
	 * @param menuDTOS
	 * @return
	 */
	List<MenuVO> buildMenu(List<MenuDTO> menuDTOS);


}
