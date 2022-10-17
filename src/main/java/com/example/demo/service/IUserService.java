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

}
