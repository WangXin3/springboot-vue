package com.wxx.springbootvue.system.mapper;

import com.wxx.springbootvue.system.domain.po.Role;

import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/14
 */
public interface RoleMapper {

    /**
     * 获取角色列表
     * @return
     */
    List<Role> getRoleList();

    /**
     * 根据用户的id查询用户角色
     * @param id
     * @return
     */
    List<Role> getRoleByUserId(Long id);

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
    int insert(Role record);

    /**
     * 新增
     * @param record /
     * @return /
     */
    int insertSelective(Role record);

    /**
     * 查询
     * @param id /
     * @return /
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record /
     * @return /
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * 更新
     * @param record /
     * @return /
     */
    int updateByPrimaryKey(Role record);
}
