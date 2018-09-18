package com.jimingqiang.study.exception;

public class AuthException extends BusinessException {

    public static final String LOGIN = "登录异常";

    public AuthException(Integer code, String msg){
        super(code,msg);
    }

}
