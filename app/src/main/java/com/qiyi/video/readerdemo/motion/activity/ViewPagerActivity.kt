package com.qiyi.video.readerdemo.motion.activity

import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.qiyi.video.readerdemo.R
import com.qiyi.video.readerdemo.motion.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.motion_16_viewpager.*

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = R.layout.motion_16_viewpager
        setContentView(layout)
        val motionLayout = findViewById<MotionLayout>(R.id.motionLayout)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addPage("Page 1", R.layout.motion_16_viewpager_page1)
        adapter.addPage("Page 2", R.layout.motion_16_viewpager_page2)
        adapter.addPage("Page 3", R.layout.motion_16_viewpager_page3)
        pager.adapter = adapter
        tabs.setupWithViewPager(pager)
        if (motionLayout != null) {
            pager.addOnPageChangeListener(motionLayout as ViewPager.OnPageChangeListener)
        }

        val doShowPaths = intent.getBooleanExtra("showPaths", false)
        motionLayout.setShowPaths(doShowPaths)
    }
}