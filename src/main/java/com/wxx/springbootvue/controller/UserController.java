package com.wxx.springbootvue.controller;

import com.wxx.springbootvue.dao.User;
import com.wxx.springbootvue.service.UserService;
import com.wxx.springbootvue.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public RespBean getUserList() {
		return RespBean.successData(userService.getUserList());
	}

	@GetMapping("/{uid}")
	public RespBean getUserById(@PathVariable Long uid) {
		return RespBean.successData(userService.getUserById(uid));
	}

	@GetMapping("/info")
	public RespBean getCurrentUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setPassword(null);
		return RespBean.successData(user);
	}
}
