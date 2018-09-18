package com.jimingqiang.study.exception;

/**
 * Created by chenzhifei on 2017/8/28.
 */
public class IgnoralAuthException extends AuthException {

    public IgnoralAuthException(Integer code, String msg) {
        super(code, msg);
    }

}
