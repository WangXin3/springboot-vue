package com.wxx.springbootvue.service;

import com.wxx.springbootvue.dao.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */

public interface UserService extends UserDetailsService {
	/**
	 * 获取用户列表
	 *
	 * @return
	 */
	List<User> getUserList();

	/**
	 * 根据用户id查询用户
	 *
	 * @param uid
	 * @return
	 */
	User getUserById(Long uid);
}
