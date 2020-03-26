package com.wxx.springbootvue.system.domain.vo;

/**
 * @author 她爱微笑
 * @date 2020/3/26
 */
public class MenuMetaVO {

	private String name;

	private String icon;

	public MenuMetaVO(String name, String icon) {
		this.name = name;
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "MenuMetaVO{" +
				"name='" + name + '\'' +
				", icon='" + icon + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
