package com.wxx.springbootvue.system.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wxx.springbootvue.system.po.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 她爱微笑
 * @date 2020/3/24
 */
public class JwtUser implements UserDetails {

	private Long id;

	private String avatar;

	private String username;

	@JsonIgnore
	private String password;

	private String email;

	private String nickname;

	/**
	 * 0=女，1=男
	 */
	private Integer gender;

	private Date createTime;

	private Date updateTime;

	private Date lastLoginTime;

	@JsonIgnore
	private Collection<GrantedAuthority> authorities;

	/**
	 * 用户状态 0=禁用，1=正常
	 */
	private Boolean enabled;

	public JwtUser(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	public JwtUser(Long id, String avatar, String username, String password, String email, String nickname, Integer gender,
				   Date createTime, Date updateTime, Date lastLoginTime, Collection<GrantedAuthority> authorities, Boolean enabled) {
		this.id = id;
		this.avatar = avatar;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.gender = gender;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.lastLoginTime = lastLoginTime;
		this.authorities = authorities;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<String> getRoles() {
		return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	/**
	 * 当前账户是否未过期
	 * @return true 未过期，false已过期
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 当前账户是否未锁定
	 * @return true 未锁定，false锁定
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 当前账户密码是否过期
	 * 某些系统要求用户定期修改密码，这个字段可用于这个功能
	 * @return true 未过期， false 已过期
	 */
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 当前账户是否可用
	 * 数据库是tinyint格式 0为false 1为true
	 * @return true 可用，false 不可用
	 */
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
}
