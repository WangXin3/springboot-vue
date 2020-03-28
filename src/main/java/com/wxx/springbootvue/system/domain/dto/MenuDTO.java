package com.wxx.springbootvue.system.domain.dto;

import com.wxx.springbootvue.system.domain.po.Menu;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/26
 */
public class MenuDTO extends Menu {

	private List<MenuDTO> children;

	public List<MenuDTO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDTO> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "MenuDTO{" +
				"children=" + children +
				'}';
	}
}
