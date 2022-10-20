package com.example.demo.controller.ex;

import com.example.demo.util.JsonResult;

public abstract class EEx extends RuntimeException {
    abstract public void setJsonRes(JsonResult<Void> jsonResult);

    public EEx() {
        super();
    }

    public EEx(String message) {
        super(message);
    }

    public EEx(String message, Throwable cause) {
        super(message, cause);
    }

    public EEx(Throwable cause) {
        super(cause);
    }

    protected EEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
