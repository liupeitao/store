package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.Date;

public interface IUserService {
    /**
     * 注册方法
     * @param user
     * @return
     */
    public Integer reg(User user);

    /**
     * 用户登录
     * @param
     * @return
     */
    public User login(String username, String passwd);

    public void changePassword(Integer uid, String oldPassword, String newPassword);

    /**2022年10月17日15:06:36
     * 根据用户id查询用户
     * @param uid
     * @return
     */
    User getByUid(Integer uid);

    /**
     * 更新用户操作
     * @param uid
     * @param username
     * @param user  用户对象的数据
     */
    void changeInfo(Integer uid, String username, User user);
}
