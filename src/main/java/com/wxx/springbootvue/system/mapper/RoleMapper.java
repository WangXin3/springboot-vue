package com.wxx.springbootvue.system.mapper;

import com.wxx.springbootvue.system.po.Role;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/14
 */
public interface RoleMapper {

	/**
	 * 获取角色列表
	 * @return
	 */
	List<Role> getRoleList();

}