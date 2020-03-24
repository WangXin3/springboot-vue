package com.wxx.springbootvue.system.controller;

import com.wxx.springbootvue.system.service.UserService;
import com.wxx.springbootvue.system.util.JwtUser;
import com.wxx.springbootvue.system.util.RespBean;
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
		JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return RespBean.successData(user);
	}
}
