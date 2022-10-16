package com.example.demo.controller;

import com.example.demo.service.ex.InsertException;
import com.example.demo.service.ex.ServiceException;
import com.example.demo.service.ex.UsernameDuplicatedException;
import com.example.demo.util.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class BaseController {
    public static  final int OK = 200;
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult jsonResult = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            jsonResult.setState(4000);
            jsonResult.setMessage("改用户名已经被注册");
        } else if (e instanceof InsertException) {
           jsonResult.setState(5000);
           jsonResult.setMessage("注册时产生的未知异常");
        }
        return jsonResult;
    }
}
