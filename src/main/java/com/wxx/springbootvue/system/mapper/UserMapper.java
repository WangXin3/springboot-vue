package com.wxx.springbootvue.system.mapper;

import com.wxx.springbootvue.system.domain.po.Role;
import com.wxx.springbootvue.system.domain.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */
public interface UserMapper {

	/**
	 * 根据用户名查询用户对象
	 *
	 * @param username
	 * @return
	 */
	User loadUserByUsername(String username);

	/**
	 * 根据用户id查询用户所具有的权限
	 *
	 * @param id
	 * @return
	 */
	List<Role> getUserRoleByUid(Long id);

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
	 *
	 * @param id /
	 * @return /
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 新增
	 *
	 * @param record /
	 * @return /
	 */
	int insert(User record);

	/**
	 * 新增
	 *
	 * @param record /
	 * @return /
	 */
	int insertSelective(User record);

	/**
	 * 查询
	 *
	 * @param id /
	 * @return /
	 */
	User selectByPrimaryKey(Integer id);

	/**
	 * 更新
	 *
	 * @param record /
	 * @return /
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * 更新
	 *
	 * @param record /
	 * @return /
	 */
	int updateByPrimaryKey(User record);

	/**
	 * 用户绑定角色
	 *
	 * @param userId /
	 * @param roles /
	 * @return /
	 */
	void insertUserBindingRole(@Param("userId") Long userId, @Param("roles")List<Role> roles);
}
