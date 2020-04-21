package com.wxx.springbootvue.system.controller;

import com.github.pagehelper.PageInfo;
import com.wxx.springbootvue.system.domain.po.User;
import com.wxx.springbootvue.system.service.UserService;
import com.wxx.springbootvue.system.util.JwtUser;
import com.wxx.springbootvue.utils.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 她爱微笑
 * @date 2020/3/8
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    /**
     * UserController日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("@wx.check('user:list')")
    public RespBean getUserList(PageInfo pageInfo) {
        logger.info("获取用户信息");
        return RespBean.successData(new PageInfo<>(userService.getUserList(pageInfo)));
    }

    @GetMapping("/{uid}")
    @PreAuthorize("@wx.check('user:list')")
    public RespBean getUserById(@PathVariable Long uid) {
        return RespBean.successData(userService.getUserById(uid));
    }

    @GetMapping("/info")
    public RespBean getCurrentUser() {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return RespBean.successData(user);
    }

    @PostMapping
    @PreAuthorize("@wx.check('user:add')")
    public RespBean addUser(@RequestBody User user) {
        userService.insertSelective(user);
        return RespBean.success("添加成功");
    }

    @DeleteMapping
    @PreAuthorize("@wx.check('user:del')")
    public RespBean delUser(@RequestBody List<User> users) {
        userService.deleteByPrimaryKey(users);
        return RespBean.success("删除成功");
    }

    @PutMapping
    @PreAuthorize("@wx.check('user:edit')")
    public RespBean editUser(@RequestBody User user) {
        userService.updateByPrimaryKeySelective(user);
        return RespBean.success("添加成功");
    }
}
