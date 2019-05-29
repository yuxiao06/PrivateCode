package com.qiyi.video.readerdemo.motion

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.constraint.motion.MotionLayout
import android.view.View
import android.widget.ImageView
import com.qiyi.video.readerdemo.R

@RequiresApi(Build.VERSION_CODES.LOLLIPOP) // for View#clipToOutline
class DemoActivity : AppCompatActivity() {
    private lateinit var container: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = intent.getIntExtra("layout_file_id", R.layout.motion_01_basic)
        setContentView(layout)
        container = findViewById(R.id.motionLayout)

        if (layout == R.layout.motion_11_coordinatorlayout) {
            val icon = findViewById<ImageView>(R.id.icon)
            icon?.clipToOutline = true
        }

        val doShowPaths = intent.getBooleanExtra("showPaths", false)
        (container as? MotionLayout)?.setShowPaths(doShowPaths)
    }

    fun changeState(v: View?) {
        val motionLayout = container as? MotionLayout ?: return
        if (motionLayout.progress > 0.5f) {
            motionLayout.transitionToStart()
        } else {
            motionLayout.transitionToEnd()
        }
    }
}