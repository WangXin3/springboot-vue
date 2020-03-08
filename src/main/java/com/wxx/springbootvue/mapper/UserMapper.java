package com.wxx.springbootvue.mapper;

import com.wxx.springbootvue.dao.Role;
import com.wxx.springbootvue.dao.User;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */
public interface UserMapper {

	/**
	 * 根据用户名查询用户对象
	 * @param username
	 * @return
	 */
	User loadUserByUsername(String username);

	/**
	 * 根据用户id查询用户所具有的权限
	 * @param id
	 * @return
	 */
	List<Role> getUserRoleByUid(Long id);
}
