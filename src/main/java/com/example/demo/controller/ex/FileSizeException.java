package com.example.demo.controller.ex;

import com.example.demo.util.JsonResult;

/** 上传的文件的大小超出了限制值 */
public class FileSizeException extends FileUploadException {
    public FileSizeException() {
        super();
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    protected FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public void setJsonRes(JsonResult<Void> jsonResult) {
        jsonResult.setState(6001);
        jsonResult.setMessage(this.getMessage());
    }
}
