package com.qiyi.video.readerdemo.motion.view

import android.content.Context
import android.support.constraint.motion.MotionHelper
import android.util.AttributeSet
import android.view.View

class FadeIn : MotionHelper {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun setProgress(view: View?, progress: Float) {
        view!!.alpha = progress
    }
}
