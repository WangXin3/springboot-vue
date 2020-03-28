package com.wxx.springbootvue.system.controller;

import com.wxx.springbootvue.system.domain.dto.MenuDTO;
import com.wxx.springbootvue.system.domain.po.Menu;
import com.wxx.springbootvue.system.service.MenuService;
import com.wxx.springbootvue.system.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

	@PostMapping
	@PreAuthorize("@wx.check('menu:add')")
	public RespBean addMenu(@Validated @RequestBody Menu menu) {
		return menuService.addMenu(menu);
	}

	@PutMapping
	@PreAuthorize("@wx.check('menu:eidt')")
	public RespBean editMenu(@Validated @RequestBody Menu menu) {
		return menuService.editMenu(menu);
	}

	@DeleteMapping
	@PreAuthorize(("@wx.check('menu:del')"))
	public RespBean delMenu(@RequestBody Menu menu) {
		return menuService.delMenu(menu);
	}
}
