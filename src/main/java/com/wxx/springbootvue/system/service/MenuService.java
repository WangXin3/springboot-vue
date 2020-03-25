package com.wxx.springbootvue.system.service;

import com.wxx.springbootvue.system.po.Menu;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/24
 */
public interface MenuService {

	/**
	 * 获取当前用户可以访问的菜单
	 * @return
	 */
	List<Menu> menuByUser();
}
