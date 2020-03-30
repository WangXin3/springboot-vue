package com.wxx.springbootvue.system.domain.po;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 她爱微笑
 * @date 2020/3/24
 */
public class Menu implements Serializable {

	private Long id;

	@NotNull(message = "上级菜单id不能为空")
	private Long parentId;

	private String name;

	private String component;

	private String icon;

	private String path;

	private String componentName;

	@NotNull(message = "资源类型不能为空")
	private Integer type;

	private String permission;

	private Date createTime;

	@Override
	public String toString() {
		return "Menu{" +
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
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
