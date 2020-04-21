package com.wxx.springbootvue.system.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 她爱微笑
 * @date 2020/3/25
 */
@Component(value = "wx")
public class WxPermissionConfig {

    /**
     * 自定义权限校验方法，admin可以访问任何接口，其他接口需要校验当前登录用户角色的权限
     * @param permissions
     * @return
     */
    public Boolean check(String ...permissions){
        // 获取当前用户的所有权限和角色
        List<String> wxRoles = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // 判断当前用户的所有权限是否包含接口上定义的权限， 拥有ROLE_ADMIN角色，所有接口都可以访问
        return wxRoles.contains("ROLE_ADMIN") || Arrays.stream(permissions).anyMatch(wxRoles::contains);
    }
}
