package com.jimingqiang.study.designpatterns.builder;

/**
 * @Auther: jimingqiang
 * @Date: 2019/3/19 20:12
 * @Description:
 */
public class App {

    public static void main(String[] args) {
        Hero hero = new Hero.Builder("张三").withAge(12).build();

        System.out.println(hero.toString());
    }
}
