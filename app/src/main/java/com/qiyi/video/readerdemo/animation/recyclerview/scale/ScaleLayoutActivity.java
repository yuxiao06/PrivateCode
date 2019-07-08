package com.qiyi.video.readerdemo.animation.recyclerview.scale;

import com.leochuan.ScaleLayoutManager;
import com.qiyi.video.readerdemo.animation.recyclerview.Util;
import com.qiyi.video.readerdemo.animation.recyclerview.VPLMActivity;


/**
 * Created by Dajavu on 27/10/2017.
 */

public class ScaleLayoutActivity extends VPLMActivity<ScaleLayoutManager, ScalePopUpWindow> {

    @Override
    protected ScaleLayoutManager createLayoutManager() {
        return new ScaleLayoutManager(this, Util.Dp2px(this, 10));
    }

    @Override
    protected ScalePopUpWindow createSettingPopUpWindow() {
        return new ScalePopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
