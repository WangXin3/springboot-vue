package com.wxx.springbootvue.dao;

import java.util.Date;

/**
 * @author 她爱微笑
 * @date 2020/3/9
 */
public class Permission {
	/**
	 *   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
	 *   `parent_id` int(11) NOT NULL COMMENT '上级资源id',
	 *   `path` varchar(255) NOT NULL COMMENT '操作/访问资源的接口地址',
	 *   `title` varchar(100) NOT NULL COMMENT '资源名称',
	 *   `icon` varchar(100) DEFAULT NULL COMMENT '资源图标',
	 *   `type` int(11) NOT NULL COMMENT '资源类型1=菜单, 2=按钮',
	 *   `description` varchar(255) DEFAULT NULL COMMENT '资源描述',
	 *   `create_time` datetime NOT NULL COMMENT '创建时间',
	 *   `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上一次修改时间',
	 */

	private Long id;

	private Long parentId;

	private String path;

	private String title;

	private String icon;

	private int type;

	private String description;

	private Date createTime;

	private Date updateTime;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
