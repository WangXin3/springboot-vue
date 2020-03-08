package com.wxx.springbootvue.config;

import com.alibaba.fastjson.JSONObject;
import com.wxx.springbootvue.dao.User;
import com.wxx.springbootvue.service.UserService;
import com.wxx.springbootvue.util.JwtUtils;
import com.wxx.springbootvue.util.RespBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */
public class JwtAuthenticationTokenFilter extends GenericFilter {


	private JwtUtils jwtUtils;


	private UserService userService;

	public JwtAuthenticationTokenFilter(JwtUtils jwtUtils, UserService userService) {
		this.jwtUtils = jwtUtils;
		this.userService = userService;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");

		HttpServletRequest request = (HttpServletRequest)servletRequest;

		// 获取 Request 中的请求头为 Authorization 的 token 值
		String completeToken = request.getHeader(jwtUtils.getTokenHeader());
		// 验证 值是否以"Wangxin3"开头
		if (completeToken != null && completeToken.startsWith(jwtUtils.getTokenHead())) {
			// 截取token中"Wangxin3"后面的值，
			final String tokenValue = jwtUtils.getCompleteToken(completeToken);

			User user;
			try {
				user = jwtUtils.getInfoFromToken(tokenValue);
			} catch (Exception e) {
				// 解析token出错， token被改
				PrintWriter out = response.getWriter();
				out.write(JSONObject.toJSONString(RespBean.error("请重新登录!")));
				out.flush();
				out.close();
				return;
			}

			// 根据 token值，获取 用户的 username
			String username = user.getUsername();
			// 验证用户账号是否合法
			if (username != null) {
				UserDetails userDetails;
				try {
					userDetails = userService.loadUserByUsername(username);
				} catch (AuthenticationException ex) {
					SecurityContextHolder.clearContext();
					return;
				}
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, user.getId(), userDetails.getAuthorities());
				// 将用户信息，设置到 SecurityContext 中
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}
}
