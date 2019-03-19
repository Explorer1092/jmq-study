package com.jimingqiang.study.guava;

import com.google.common.base.Preconditions;

/**
 * @Auther: jimingqiang
 * @Date: 2019/3/19 14:54
 * @Description:
 */
public class GuavaPreconditions {

    public static void main(String[] args) {
        try {
            Preconditions.checkArgument( 1 != 1,"222222","1=1=1",333);
        } catch (IllegalArgumentException e) {
            System.out.println(1111);
            e.printStackTrace();
        }
    }
}
