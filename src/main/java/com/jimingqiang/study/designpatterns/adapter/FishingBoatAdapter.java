package com.jimingqiang.study.designpatterns.adapter;

/**
 * @Auther: jimingqiang
 * @Date: 2019/3/19 20:28
 * @Description:
 */
public class FishingBoatAdapter implements RowingBoat {

    private FishingBoat fishingBoat;

    public FishingBoatAdapter() {
        this.fishingBoat = new FishingBoat();
    }

    @Override
    public void run() {
        fishingBoat.run();
    }
}
