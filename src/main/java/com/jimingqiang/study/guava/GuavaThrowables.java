package com.jimingqiang.study.guava;

import com.google.common.base.Throwables;

/**
 * Created by QDHL on 2018/9/16.
 *
 * @author mingqiang ji
 */
public class GuavaThrowables {

    public static void main(String[] args) {
        int a = 0;
        int b = 1;

        try {
            int c = b/a ;
        } catch (Exception e) {

            //System.out.println(Throwables.getRootCause(e));
            System.out.println(Throwables.getStackTraceAsString(e));

            e.printStackTrace();
        }
    }


}
