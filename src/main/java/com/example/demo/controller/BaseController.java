package com.example.demo.controller;

import com.example.demo.controller.ex.EEx;
import com.example.demo.controller.ex.FileUploadException;
import com.example.demo.service.ex.InsertException;
import com.example.demo.service.ex.ServiceException;
import com.example.demo.service.ex.UsernameDuplicatedException;
import com.example.demo.util.JsonResult;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    public static  final int OK = 200;
    @ExceptionHandler({EEx.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult jsonResult = new JsonResult<>(e);
        EEx eex = (EEx) e;
        eex.setJsonRes(jsonResult);
        return jsonResult;
    }

    public Integer getUidFromSession(HttpSession httpSession){
        return Integer.valueOf(httpSession.getAttribute("uid").toString());
    }
    public String getUserNameFromSession(HttpSession httpSession){
        return httpSession.getAttribute("username").toString();
    }
}
