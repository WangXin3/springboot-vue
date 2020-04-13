package com.wxx.springbootvue.system.util;

import com.wxx.springbootvue.utils.ObjectUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Wang
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

	/**
	 * 密匙KEY
	 */
	private String secret;

	/**
	 * 过期时间 单位分钟
	 */
	private Integer expire;

	/**
	 * 请求头名称
	 */
	private String tokenHeader;

	/**
	 * Token前缀字符
	 */
	private String tokenHead;

	/**
	 * 不需要认证就可以访问的url
	 */
	private List<String> ignoreUrl;

	/**
	 * 生成token
	 *
	 * @param user 载荷中的数据
	 * @return
	 * @throws Exception
	 */
	public String generateToken(JwtUser user) {
		return Jwts.builder()
				.claim(JwtConstans.JWT_KEY_ID, user.getId())
				.claim(JwtConstans.JWT_KEY_USER_NAME, user.getUsername())
				.setExpiration(DateTime.now().plusMinutes(this.expire).toDate())
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}


	/**
	 * 获取token中的用户信息
	 *
	 * @param token 用户请求中的令牌
	 * @return 用户信息
	 * @throws Exception
	 */
	public JwtUser getInfoFromToken(String token) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
		Claims body = claimsJws.getBody();
		return new JwtUser(
				ObjectUtils.toLong(body.get(JwtConstans.JWT_KEY_ID)),
				ObjectUtils.toString(body.get(JwtConstans.JWT_KEY_USER_NAME))
		);
	}

	/**
	 * 获取去掉令牌头之后的token
	 * @param token
	 * @return
	 */
	public String getCompleteToken(String token) {
		return token.substring(this.getTokenHead().length());
	}


	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Integer getExpire() {
		return expire;
	}

	public void setExpire(Integer expire) {
		this.expire = expire;
	}

	public String getTokenHeader() {
		return tokenHeader;
	}

	public void setTokenHeader(String tokenHeader) {
		this.tokenHeader = tokenHeader;
	}

	public String getTokenHead() {
		return tokenHead;
	}

	public void setTokenHead(String tokenHead) {
		this.tokenHead = tokenHead;
	}

	public List<String> getIgnoreUrl() {
		return ignoreUrl;
	}

	public void setIgnoreUrl(List<String> ignoreUrl) {
		this.ignoreUrl = ignoreUrl;
	}
}