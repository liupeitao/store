package com.example.demo.controller;

import com.example.demo.service.ex.InsertException;
import com.example.demo.service.ex.ServiceException;
import com.example.demo.service.ex.UsernameDuplicatedException;
import com.example.demo.util.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;


public class BaseController {
    public static  final int OK = 200;
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult jsonResult = new JsonResult<>(e);
        ServiceException serviceException = (ServiceException)e;
        serviceException.setJsonRes(jsonResult);
        return jsonResult;
    }

    public Integer getUidFromSession(HttpSession httpSession){
        return Integer.valueOf(httpSession.getAttribute("uid").toString());
    }
    public String getUserNameFromSession(HttpSession httpSession){
        return httpSession.getAttribute("username").toString();
    }
}
