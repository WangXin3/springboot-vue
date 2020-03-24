package com.wxx.springbootvue.system.service.impl;

import com.wxx.springbootvue.system.po.User;
import com.wxx.springbootvue.system.mapper.UserMapper;
import com.wxx.springbootvue.system.service.UserService;
import com.wxx.springbootvue.system.util.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// 根据用户名查询用户
		JwtUser jwtUser = userMapper.loadJwtUserByUsername(username);
		if (jwtUser == null) {
			throw new UsernameNotFoundException("账户不存在!");
		}
		// 根据用户id查询用户所具有的角色并设置到user对象中
		jwtUser.setRoles(userMapper.getUserRoleByUid(jwtUser.getId()));
		return jwtUser;
	}

	@Override
	public List<User> getUserList() {
		return userMapper.getUserList();
	}

	@Override
	public User getUserById(Long uid) {
		return userMapper.getUserById(uid);
	}
}
