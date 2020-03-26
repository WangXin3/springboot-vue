package com.wxx.springbootvue.system.controller;

import com.wxx.springbootvue.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 她爱微笑
 * @date 2020/3/24
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping("/build")
	public ResponseEntity findByUser() {
		return ResponseEntity.ok(menuService.findByUser());
	}

	@GetMapping("/tree")
	@PreAuthorize("@wx.check('menu:list')")
	public ResponseEntity findList() {
		return ResponseEntity.ok(menuService.findTreeList(menuService.findByPid(0L)));
	}
}
