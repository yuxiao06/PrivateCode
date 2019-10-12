package com.qiyi.video.readerdemo

import com.qiyi.video.readerdemo.animation.recyclerview.VPLMMainActivity
import com.qiyi.video.readerdemo.dispatcher.DispatcherActivity
import com.qiyi.video.readerdemo.motion.activity.MotionActivity
import com.qiyi.video.readerdemo.plugin.PluginActivity
import com.qiyi.video.readerdemo.viewDragHelper.activity.ViewDragActivity

object MainListDatas {
    val datas: Array<MainListData> = arrayOf(
            MainListData("Motion Demo", "Motion动画演示", MotionActivity::class.java),
            MainListData("任务分发Demo", "将任务按依赖关系合理分配到cpu密集型线程池", DispatcherActivity::class.java),
            MainListData("动态页面Demo", "加载SD卡apk", PluginActivity::class.java),
            MainListData("动画", "Recyclerview实现Viewpager效果和", VPLMMainActivity::class.java),
            MainListData("ViewDragHelper", "View随手指移动效果", ViewDragActivity::class.java)
    )
}