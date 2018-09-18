package com.jimingqiang.study.controller;

import com.jimingqiang.study.exception.AuthException;
import com.jimingqiang.study.exception.BusinessException;
import com.jimingqiang.study.response.HttpCodeEnum;
import com.jimingqiang.study.response.HttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by QDHL on 2018/9/18.
 *
 * @author mingqiang ji
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        try {
            response.getWriter().write(HttpResponse.createFail(HttpCodeEnum.SERVER_ERROR.getCode(), "系统异常:" + e.getMessage()).toString());
        } catch (Exception ex) {

        }
    }


    @ExceptionHandler(AuthException.class)
    public void handleAuthException(HttpServletRequest request, HttpServletResponse response, AuthException e) {
        try {
            response.getWriter().write(HttpResponse.createFail(e.getCode(), "系统异常:" + e.getMsg()).toString());
            System.out.println("=============" + e.getMsg());
        } catch (Exception ex) {
        }
    }

    @ExceptionHandler(BusinessException.class)
    public void handleBusienssException(HttpServletRequest request, HttpServletResponse response, BusinessException e) {
        try {
            response.getWriter().write(HttpResponse.createFail(e.getCode(), "业务异常:" + e.getMsg()).toString());
        } catch (Exception ex) {
        }
    }



}
