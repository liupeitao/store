package com.example.demo.service.ex;

import com.example.demo.util.JsonResult;

public class PassWordNotMatchException extends ServiceException{
    public PassWordNotMatchException() {
        super();
    }

    public PassWordNotMatchException(String message) {
        super(message);
    }

    public PassWordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PassWordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected PassWordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public void setJsonRes(JsonResult<Void> jsonResult) {
        jsonResult.setState(5002);
        jsonResult.setMessage("用户名密码不匹配异常");
    }
}
