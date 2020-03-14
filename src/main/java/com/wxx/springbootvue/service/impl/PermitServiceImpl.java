package com.wxx.springbootvue.service.impl;

import com.wxx.springbootvue.dao.Permission;
import com.wxx.springbootvue.mapper.PermitMapper;
import com.wxx.springbootvue.service.PermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/14
 */
@Service
public class PermitServiceImpl implements PermitService {

	@Autowired
	private PermitMapper permitMapper;

	@Override
	public List<Permission> getPermitListById(Long id) {
		return permitMapper.getPermitListById(id);
	}
}
