
package com.example.demo.controller.ex;

import com.example.demo.util.JsonResult;

/** 文件上传相关异常的基类 */
abstract public class FileUploadException  extends EEx {
    public FileUploadException() {
        super();
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    protected FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public abstract void setJsonRes(JsonResult<Void> jsonResult);
}
