package com.wxx.springbootvue.system.config;

import com.alibaba.fastjson.JSONObject;
import com.wxx.springbootvue.system.domain.po.User;
import com.wxx.springbootvue.system.service.UserService;
import com.wxx.springbootvue.system.util.JwtUser;
import com.wxx.springbootvue.system.util.JwtUtils;
import com.wxx.springbootvue.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private JwtUtils jwtUtils;

    private UserService userService;

    protected JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        JwtUser user = (JwtUser) authResult.getPrincipal();

        User u = new User();
        u.setId(user.getId());
        u.setLastLoginTime(new Date());

        // 更新最后一次登录时间
        userService.updateByPrimaryKeySelective(u);

        String token = jwtUtils.generateToken(user);
        token = jwtUtils.getTokenHead() + token;
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(RespBean.success("登录成功！", token)));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(RespBean.error("账号/密码错误或账号被冻结！")));
        out.flush();
        out.close();
    }
}
