package com.blog.gfblog.exception;

/**
 * 自定义业务异常处理
 * @author husir
 * @date 2024/05/26
 */
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    public String errorCode;
    /**
     * 错误信息
     */
    public String errorMsg;

    public BusinessException() {
        super();
    }

    public BusinessException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface.getCode().toString());
        this.errorCode = errorInfoInterface.getCode().toString();
        this.errorMsg = errorInfoInterface.getMsg();
    }

    public BusinessException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getCode().toString(), cause);
        this.errorCode = errorInfoInterface.getCode().toString();
        this.errorMsg = errorInfoInterface.getMsg();
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
