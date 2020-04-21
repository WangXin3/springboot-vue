package com.wxx.springbootvue.system.config;

import com.alibaba.fastjson.JSONObject;
import com.wxx.springbootvue.system.service.UserService;
import com.wxx.springbootvue.system.util.JwtUtils;
import com.wxx.springbootvue.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */
@Configuration
@EnableConfigurationProperties(JwtUtils.class)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtLoginFilter("/login", authenticationManager(), jwtUtils, userService),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationTokenFilter(jwtUtils, userService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    // 自定义 401 Unauthorized 未授权，就是没有登录，header中没有传Token
                    PrintWriter out = response.getWriter();
                    out.write(JSONObject.toJSONString(RespBean.error("请先登录!", HttpStatus.UNAUTHORIZED)));
                    out.flush();
                    out.close();
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    // 自定义 403 Forbidden 拒绝访问，就是你没有权限访问这个接口
                    PrintWriter out = response.getWriter();
                    out.write(JSONObject.toJSONString(RespBean.error("无权限!", HttpStatus.FORBIDDEN)));
                    out.flush();
                    out.close();
                })

                .and()
                .csrf().disable().cors()

                // 禁用session会话，要不然第一次认证成功之后会存在会话信息，第二次不携带token就会成功访问接口
                // SecurityContextHolder.getContext().getAuthentication() 中会缓存信息，如果未携带token，没有重新设置Authentication
                // 就不会经过authenticationEntryPoint过滤器，抛出异常信息
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
