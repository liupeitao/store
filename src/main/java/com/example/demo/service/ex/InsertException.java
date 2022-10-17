package com.example.demo.service.ex;

import com.example.demo.util.JsonResult;

public class InsertException extends ServiceException {
    public InsertException() {
        super();
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public void setJsonRes(JsonResult<Void> jsonResult) {
        jsonResult.setMessage(this.getMessage());
        jsonResult.setState(5000);
    }
}
