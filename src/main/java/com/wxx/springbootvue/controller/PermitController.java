package com.wxx.springbootvue.controller;

import com.wxx.springbootvue.service.PermitService;
import com.wxx.springbootvue.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 她爱微笑
 * @date 2020/3/14
 * 权限控制层
 */
@RestController
@RequestMapping("/system/permission")
public class PermitController {

	@Autowired
	private PermitService permitService;

	@GetMapping("/{id}")
	public RespBean getPermitList(@PathVariable Long id) {
		if (id == null) {
			id = 0L;
		}

		return RespBean.successData(permitService.getPermitListById(id));
	}
}
