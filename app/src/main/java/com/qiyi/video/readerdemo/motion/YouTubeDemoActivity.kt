package com.qiyi.video.readerdemo.motion

import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.qiyi.video.readerdemo.R

class YouTubeDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motion_24_youtube)
        val motionLayout = findViewById<MotionLayout>(R.id.motionLayout).apply {
            savedInstanceState
        }
        findViewById<RecyclerView>(R.id.recyclerview_front).apply {
            adapter = FrontPhotosAdapter()
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@YouTubeDemoActivity)
        }
        val doShowPaths = intent.getBooleanExtra("showPaths", false)
        motionLayout.setShowPaths(doShowPaths)
    }
}