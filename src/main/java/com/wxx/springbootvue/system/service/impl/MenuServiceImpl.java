package com.wxx.springbootvue.system.service.impl;

import com.wxx.springbootvue.system.mapper.MenuMapper;
import com.wxx.springbootvue.system.po.Menu;
import com.wxx.springbootvue.system.service.MenuService;
import com.wxx.springbootvue.system.util.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/24
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> menuByUser() {
		JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return menuMapper.menuByUserId(user.getId());
	}
}
