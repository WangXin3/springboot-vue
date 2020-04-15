package com.wxx.springbootvue.system.service.impl;

import com.wxx.springbootvue.system.domain.po.Role;
import com.wxx.springbootvue.system.domain.po.User;
import com.wxx.springbootvue.system.mapper.RoleMapper;
import com.wxx.springbootvue.system.mapper.UserMapper;
import com.wxx.springbootvue.system.service.RoleService;
import com.wxx.springbootvue.system.service.UserService;
import com.wxx.springbootvue.system.util.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// 根据用户名查询用户
		User user = userMapper.loadUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("账户不存在!");
		}
		return createJwtUser(user);
	}

	/**
	 * 根据user对象生成一个JwtUser对象
	 * @param user
	 * @return
	 */
	private UserDetails createJwtUser(User user) {
		return new JwtUser(
				user.getId(),
				user.getAvatar(),
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				user.getNickname(),
				user.getGender(),
				user.getCreateTime(),
				user.getUpdateTime(),
				user.getLastLoginTime(),
				roleService.getGrantedAuthorities(user),
				user.getEnabled()
		);
	}

	@Override
	public List<User> getUserList() {
		return userMapper.getUserList();
	}

	@Override
	public User getUserById(Long uid) {
		return userMapper.getUserById(uid);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		int userId = userMapper.insertSelective(record);
		List<Role> roles = record.getRoles();

		Map<String, Object> map = new HashMap<>(2);
		map.put("userId", userId);
		map.put("roles", roles);

		userMapper.insertUserBindingRole(map);

		return userId;
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}
}
