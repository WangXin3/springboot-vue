package com.wxx.springbootvue.mapper;

import com.wxx.springbootvue.dao.Permission;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/14
 */
public interface PermitMapper {

	/**
	 * 根据父菜单id查询菜单或按钮
	 * @param id
	 * @return
	 */
	List<Permission> getPermitListById(Long id);
}
