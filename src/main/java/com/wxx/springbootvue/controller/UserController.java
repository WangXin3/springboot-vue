package com.wxx.springbootvue.controller;

import com.wxx.springbootvue.util.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/hello")
	public RespBean hello() {
		return RespBean.success("hello world!");
	}
}
