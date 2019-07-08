package com.qiyi.video.readerdemo.animation.recyclerview.gallery;

import com.leochuan.GalleryLayoutManager;
import com.qiyi.video.readerdemo.animation.recyclerview.Util;
import com.qiyi.video.readerdemo.animation.recyclerview.VPLMActivity;

/**
 * Created by Dajavu on 27/10/2017.
 */

public class GalleryLayoutActivity extends VPLMActivity<GalleryLayoutManager, GalleryPopUpWindow> {

    @Override
    protected GalleryLayoutManager createLayoutManager() {
        return new GalleryLayoutManager(this, Util.Dp2px(this, 10));
    }

    @Override
    protected GalleryPopUpWindow createSettingPopUpWindow() {
        return new GalleryPopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
