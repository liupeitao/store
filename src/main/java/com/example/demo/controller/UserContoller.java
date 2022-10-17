package com.example.demo.controller;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.InsertException;
import com.example.demo.service.ex.UsernameDuplicatedException;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserContoller extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
       userService.reg(user);
       return new JsonResult<>(200);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username,
                                String password,
                                HttpSession httpSession){
        User user = userService.login(username, password);
        httpSession.setAttribute("uid", user.getUid());
        httpSession.setAttribute("username", user.getUsername());

        System.out.println(getUidFromSession(httpSession));
        System.out.println(getUserNameFromSession(httpSession));
        return  new JsonResult<User>(OK, user);
    }
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession httpSession){
        Integer uid = (Integer) httpSession.getAttribute("uid");
        userService.changePassword(uid, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }
}
