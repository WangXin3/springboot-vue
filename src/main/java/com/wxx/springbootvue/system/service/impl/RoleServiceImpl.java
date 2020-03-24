package com.wxx.springbootvue.system.service.impl;

import com.wxx.springbootvue.system.po.Role;
import com.wxx.springbootvue.system.mapper.RoleMapper;
import com.wxx.springbootvue.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/14
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;


	@Override
	public List<Role> getRoleList() {
		return roleMapper.getRoleList();
	}
}
