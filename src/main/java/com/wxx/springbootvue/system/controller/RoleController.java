package com.wxx.springbootvue.system.controller;

import com.wxx.springbootvue.system.service.RoleService;
import com.wxx.springbootvue.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("@wx.check('role:list')")
	public RespBean roleList() {
		return RespBean.successData(roleService.getRoleList());
	}
}
