package com.jimingqiang.study.controller;

import com.jimingqiang.study.exception.BusinessException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by QDHL on 2018/9/18.
 *
 * @author mingqiang ji
 */
@RestController
@RequestMapping(value="/global")
public class GlobalExceptionController {


    @RequestMapping(value="/exception", method= RequestMethod.GET)
    public String postUser() {
        int a = 0;
        int b = 10;
        int c = b/a;
        return "success";
    }
}
