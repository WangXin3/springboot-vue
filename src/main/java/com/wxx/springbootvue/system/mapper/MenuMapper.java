package com.wxx.springbootvue.system.mapper;

import com.wxx.springbootvue.system.domain.dto.MenuDTO;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/24
 */
public interface MenuMapper {

	/**
	 * 获取当前用户可以访问的菜单
	 *
	 * @param id
	 * @return
	 */
	List<MenuDTO> menuByUserId(Long id);

	/**
	 * 根据父菜单id查询子菜单
	 * @param pid
	 * @return
	 */
	List<MenuDTO> findByPid(Long pid);
}
