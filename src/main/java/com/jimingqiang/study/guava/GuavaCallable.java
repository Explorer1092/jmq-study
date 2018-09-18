package com.jimingqiang.study.guava;

import java.util.concurrent.Callable;

/**
 * Created by QDHL on 2018/9/17.
 *
 * @author mingqiang ji
 */
public class GuavaCallable {



    public static int getInt(int a, Callable<Integer> value ) throws Exception {
        int a0 =a;
        Integer call = 0;
        if(a ==5){
             call = value.call();
        }


        return a0 + call;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(getInt(5, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 5;
            }
        }));

    }
}
