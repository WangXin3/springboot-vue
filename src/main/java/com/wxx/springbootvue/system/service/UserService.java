package com.wxx.springbootvue.system.service;

import com.wxx.springbootvue.system.domain.po.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */

public interface UserService extends UserDetailsService {
	/**
	 * 获取用户列表
	 *
	 * @return
	 */
	List<User> getUserList();

	/**
	 * 根据用户id查询用户
	 *
	 * @param uid
	 * @return
	 */
	User getUserById(Long uid);

	/**
	 * 删除
	 * @param id /
	 * @return /
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 新增
	 * @param record /
	 * @return /
	 */
	int insert(User record);

	/**
	 * 新增
	 * @param record /
	 * @return /
	 */
	int insertSelective(User record);

	/**
	 * 查询
	 * @param id /
	 * @return /
	 */
	User selectByPrimaryKey(Integer id);

	/**
	 * 更新
	 * @param record /
	 * @return /
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * 更新
	 * @param record /
	 * @return /
	 */
	int updateByPrimaryKey(User record);
}
