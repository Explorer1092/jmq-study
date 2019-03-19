package com.jimingqiang.study.springtest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AdviseDefine {

    @Pointcut("execution(* com.jimingqiang.study.springtest.aop.AopBean.*(..))")
    public void pointCut(){

    }

    // 定义 advise
    @Around("pointCut()")
    public void doAroundAccessCheck(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入环绕通知");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }

        Object target = pjp.getTarget();
        System.out.println(target);
        Object aThis = pjp.getThis();
        System.out.println(aThis);

        String[] s = {"11111"};
        // 开始
        pjp.proceed(s);
        // 结束
        System.out.println("出来环绕通知****");
    }
}
