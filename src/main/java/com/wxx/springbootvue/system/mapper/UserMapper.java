package com.wxx.springbootvue.system.mapper;

import com.wxx.springbootvue.system.po.Role;
import com.wxx.springbootvue.system.po.User;
import com.wxx.springbootvue.system.util.JwtUser;

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
	JwtUser loadJwtUserByUsername(String username);

	/**
	 * 根据用户id查询用户所具有的权限
	 * @param id
	 * @return
	 */
	List<Role> getUserRoleByUid(Long id);

	/**
	 * 获取用户列表
	 * @return
	 */
	List<User> getUserList();

	/**
	 * 根据用户id查询用户
	 * @param uid
	 * @return
	 */
	User getUserById(Long uid);
}
