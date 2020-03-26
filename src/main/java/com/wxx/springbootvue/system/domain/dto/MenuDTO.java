package com.wxx.springbootvue.system.domain.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/26
 */
public class MenuDTO implements Serializable {

	private Long id;

	private Long parentId;

	private String name;

	private String component;

	private String icon;

	private String path;

	private String componentName;

	private Integer type;

	private String permission;

	private Date createTime;

	private List<MenuDTO> children;

	@Override
	public String toString() {
		return "MenuDTO{" +
				"id=" + id +
				", parentId=" + parentId +
				", name='" + name + '\'' +
				", component='" + component + '\'' +
				", icon='" + icon + '\'' +
				", path='" + path + '\'' +
				", componentName='" + componentName + '\'' +
				", type=" + type +
				", permission='" + permission + '\'' +
				", createTime=" + createTime +
				", children=" + children +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<MenuDTO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDTO> children) {
		this.children = children;
	}
}
