package com.jimingqiang.study.exception;

/**  业务异常
 * Created by libo on 2017/11/9.
 */
public class BusinessException extends RuntimeException {

    protected Integer code;
    protected String msg;

    public BusinessException(Integer code, String msg) {
        super(code + msg);
        this.code = code;
        this.msg = code + msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
