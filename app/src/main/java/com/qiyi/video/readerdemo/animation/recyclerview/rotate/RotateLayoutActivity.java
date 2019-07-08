package com.qiyi.video.readerdemo.animation.recyclerview.rotate;

import com.leochuan.RotateLayoutManager;
import com.qiyi.video.readerdemo.animation.recyclerview.Util;
import com.qiyi.video.readerdemo.animation.recyclerview.VPLMActivity;


/**
 * Created by Dajavu on 27/10/2017.
 */

public class RotateLayoutActivity extends VPLMActivity<RotateLayoutManager, RotatePopUpWindow> {

    @Override
    protected RotateLayoutManager createLayoutManager() {
        return new RotateLayoutManager(this, Util.Dp2px(this, 10));
    }

    @Override
    protected RotatePopUpWindow createSettingPopUpWindow() {
        return new RotatePopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
