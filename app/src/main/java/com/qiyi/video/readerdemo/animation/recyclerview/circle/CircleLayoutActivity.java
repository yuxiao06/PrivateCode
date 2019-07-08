package com.qiyi.video.readerdemo.animation.recyclerview.circle;

import com.leochuan.CircleLayoutManager;
import com.qiyi.video.readerdemo.animation.recyclerview.VPLMActivity;
import com.qiyi.video.readerdemo.animation.recyclerview.circle.CirclePopUpWindow;

/**
 * Created by Dajavu on 25/10/2017.
 */

public class CircleLayoutActivity extends VPLMActivity<CircleLayoutManager, CirclePopUpWindow> {

    @Override
    protected CircleLayoutManager createLayoutManager() {
        return new CircleLayoutManager(this);
    }

    @Override
    protected CirclePopUpWindow createSettingPopUpWindow() {
        return new CirclePopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
