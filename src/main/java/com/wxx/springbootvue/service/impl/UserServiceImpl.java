package com.wxx.springbootvue.service.impl;

import com.wxx.springbootvue.dao.User;
import com.wxx.springbootvue.mapper.UserMapper;
import com.wxx.springbootvue.service.UserService;
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
		User user = userMapper.loadUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("账户不存在!");
		}
		// 根据用户id查询用户所具有的角色并设置到user对象中
		user.setRoles(userMapper.getUserRoleByUid(user.getId()));
		return user;
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
