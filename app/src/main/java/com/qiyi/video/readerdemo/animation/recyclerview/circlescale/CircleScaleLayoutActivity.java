package com.qiyi.video.readerdemo.animation.recyclerview.circlescale;

import com.leochuan.CircleScaleLayoutManager;
import com.qiyi.video.readerdemo.animation.recyclerview.VPLMActivity;
import com.qiyi.video.readerdemo.animation.recyclerview.circlescale.CircleScalePopUpWindow;

/**
 * Created by Dajavu on 27/10/2017.
 */

public class CircleScaleLayoutActivity extends VPLMActivity<CircleScaleLayoutManager, CircleScalePopUpWindow> {

    @Override
    protected CircleScaleLayoutManager createLayoutManager() {
        return new CircleScaleLayoutManager(this);
    }

    @Override
    protected CircleScalePopUpWindow createSettingPopUpWindow() {
        return new CircleScalePopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
