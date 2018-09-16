package com.jimingqiang.study.guava;


import com.google.common.base.Optional;

/**
 * Created by QDHL on 2018/9/15.
 *
 * @author mingqiang ji
 */
public class GuavaOptional {
    public static void main(String[] args) {
        Integer value1 =  10;
        Optional<Integer> of = Optional.of(value1);
        Optional<Integer> integerOptional = Optional.fromNullable(value1);
        System.out.println(integerOptional.isPresent());
        //System.out.println(integerOptional.get());
        System.out.println(integerOptional.or(5));
    }
}
