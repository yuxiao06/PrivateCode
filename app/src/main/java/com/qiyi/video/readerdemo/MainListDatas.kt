package com.qiyi.video.readerdemo

import com.qiyi.video.readerdemo.dispatcher.DispatcherActivity
import com.qiyi.video.readerdemo.motion.activity.MotionActivity

object MainListDatas {
    val datas: Array<MainListData> = arrayOf(
            MainListData("Motion Demo", "Motion动画演示", MotionActivity::class.java),
            MainListData("任务分发Demo","将任务按依赖关系合理分配到cpu密集型线程池", DispatcherActivity::class.java)
    )
}