package com.wxx.springbootvue.system.controller;

import com.wxx.springbootvue.system.service.UserService;
import com.wxx.springbootvue.system.util.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("@wx.check('user:list')")
	public ResponseEntity getUserList() {
		return ResponseEntity.ok(userService.getUserList());
	}

	@GetMapping("/{uid}")
	@PreAuthorize("@wx.check('user:list')")
	public ResponseEntity getUserById(@PathVariable Long uid) {
		return ResponseEntity.ok(userService.getUserById(uid));
	}

	@GetMapping("/info")
	public ResponseEntity getCurrentUser() {
		JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(user);
	}
}
