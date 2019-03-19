package com.jimingqiang.study.designpatterns.adapter;

/**
 * @Auther: jimingqiang
 * @Date: 2019/3/19 20:30
 * @Description:
 */
public class app {

    public static void main(String[] args) {
        Captain captain = new Captain(new FishingBoatAdapter());

        captain.run();
    }
}
