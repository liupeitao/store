package com.example.demo.controller.ex;

import com.example.demo.util.JsonResult;

/** 上传的文件为空的异常，例如没有选择上传的文件就提交了表单，或选择的文件是0字节的空文件 */
public class FileEmptyException extends FileUploadException {
    public FileEmptyException() {
        super();
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    protected FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public void setJsonRes(JsonResult<Void> jsonResult) {
        jsonResult.setState(6000);
        jsonResult.setMessage(this.getMessage());
    }
}
