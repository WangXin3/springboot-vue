package com.wxx.springbootvue.system.po;

import java.util.Date;

/**
 * @author 她爱微笑
 * @date 2020/3/24
 */
public class Menu {

	/**
	 * `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
	 * `parent_id` int(11) NOT NULL COMMENT '上级菜单id',
	 * `title` varchar(100) DEFAULT NULL COMMENT '菜单名称',
	 * `component` varchar(255) DEFAULT NULL COMMENT '组件',
	 * `icon` varchar(100) DEFAULT NULL COMMENT '资源图标',
	 * `path` varchar(255) DEFAULT NULL COMMENT '操作/访问资源的接口地址',
	 * `component_name` varchar(255) DEFAULT NULL COMMENT '组件名称',
	 * `type` int(11) NOT NULL COMMENT '资源类型1=顶级菜单, 2=二级菜单，3=按钮',
	 * `permission` varchar(255) DEFAULT NULL COMMENT '权限',
	 * `create_time` datetime NOT NULL COMMENT '创建时间',
	 */

	private Integer id;

	private Integer parentId;

	private String title;

	private String component;

	private String icon;

	private String path;

	private String componentName;

	private Integer type;

	private String permission;

	private Date createTime;

	@Override
	public String toString() {
		return "Menu{" +
				"id=" + id +
				", parentId=" + parentId +
				", title='" + title + '\'' +
				", component='" + component + '\'' +
				", icon='" + icon + '\'' +
				", path='" + path + '\'' +
				", componentName='" + componentName + '\'' +
				", type=" + type +
				", permission='" + permission + '\'' +
				", createTime=" + createTime +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
