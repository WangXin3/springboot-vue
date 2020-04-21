package com.wxx.springbootvue.system.service.impl;

import com.wxx.springbootvue.system.domain.po.Menu;
import com.wxx.springbootvue.system.domain.po.Role;
import com.wxx.springbootvue.system.mapper.RoleMapper;
import com.wxx.springbootvue.system.domain.po.User;
import com.wxx.springbootvue.system.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Collection<GrantedAuthority> getGrantedAuthorities(User user) {
        List<Role> roles = roleMapper.getRoleByUserId(user.getId());
        // 先把角色添加到权限
        Set<String> permissions = roles.stream().filter(role -> StringUtils.isNotBlank(role.getRoleName()))
                .map(Role::getRoleName).collect(Collectors.toSet());

        // flatMap把每个role中的所有菜单都压入一个流中，统一处理
        permissions.addAll(
                roles.stream().flatMap(role -> role.getMenus().stream())
                        .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                        .map(Menu::getPermission).collect(Collectors.toSet())
        );

        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }


}
