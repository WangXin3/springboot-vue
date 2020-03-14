package com.wxx.springbootvue.controller;

import com.wxx.springbootvue.service.RoleService;
import com.wxx.springbootvue.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 她爱微笑
 * @date 2020/3/14
 * 角色控制层
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public RespBean roleList() {
		return RespBean.success(roleService.getRoleList());
	}
}
