package com.qiyi.video.readerdemo.animation.recyclerview.carousel;

import com.leochuan.CarouselLayoutManager;
import com.qiyi.video.readerdemo.animation.recyclerview.Util;
import com.qiyi.video.readerdemo.animation.recyclerview.VPLMActivity;

/**
 * Created by Dajavu on 27/10/2017.
 */

public class CarouselLayoutActivity extends VPLMActivity<CarouselLayoutManager, CarouselPopUpWindow> {

    @Override
    protected CarouselLayoutManager createLayoutManager() {
        return new CarouselLayoutManager(this, Util.Dp2px(this, 100));
    }

    @Override
    protected CarouselPopUpWindow createSettingPopUpWindow() {
        return new CarouselPopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
