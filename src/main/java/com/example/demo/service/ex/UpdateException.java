package com.example.demo.service.ex;

import com.example.demo.util.JsonResult;

public class UpdateException extends ServiceException{
    @Override
    public void setJsonRes(JsonResult<Void> jsonResult) {
        jsonResult.setState(5005);
        jsonResult.setMessage(this.getMessage());
    }

    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
