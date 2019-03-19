package com.jimingqiang.study.designpatterns.adapter;

/**
 * @Auther: jimingqiang
 * @Date: 2019/3/19 20:31
 * @Description:
 */
public class Captain {

    private RowingBoat rowingBoat;

    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    public void run(){
        rowingBoat.run();
    }
}
