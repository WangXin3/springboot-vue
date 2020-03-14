package com.wxx.springbootvue.controller;

import com.wxx.springbootvue.service.UserService;
import com.wxx.springbootvue.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
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
		return RespBean.success(userService.getUserList());
	}

	@GetMapping("/{uid}")
	public RespBean getUserById(@PathVariable Long uid) {
		return RespBean.success(userService.getUserById(uid));
	}
}
