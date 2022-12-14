package com.qiyi.video.readerdemo.motion.activity

import android.app.ListFragment
import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.qiyi.video.readerdemo.R
import com.qiyi.video.readerdemo.motion.fragment.MainFragment
import com.qiyi.video.readerdemo.motion.fragment.SecondFragment
import kotlinx.android.synthetic.main.motion_activity.*

class FragmentExample2Activity : AppCompatActivity(), View.OnClickListener, MotionLayout.TransitionListener {
    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, progress: Float) {
        if (progress - lastProgress > 0) {
            // from start to end
            val atEnd = Math.abs(progress - 1f) < 0.1f
            if (atEnd && fragment is MainFragment) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction
                        .setCustomAnimations(R.animator.show, 0)
                fragment = com.qiyi.video.readerdemo.motion.fragment.ListFragment.newInstance()
                transaction
                        .replace(R.id.container, fragment!!)
                        .commitNow()
            }
        } else {
            // from end to start
            val atStart = progress < 0.9f
            if (atStart && fragment is ListFragment) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction
                        .setCustomAnimations(0, R.animator.hide)
                fragment = MainFragment.newInstance()
                transaction
                        .replace(R.id.container, fragment!!)
                        .commitNow()
            }
        }
        lastProgress = progress
    }

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
    }

    private var lastProgress = 0f

    private var fragment : Fragment? = null
    private var last : Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motion_activity)
        if (savedInstanceState == null) {
            fragment = MainFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment!!)
                    .commitNow()
        }
        //toggle.setOnClickListener(this)
        //toggleAnimation.setOnClickListener(this)
        motionLayout.setTransitionListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.toggle) {
            val transaction = supportFragmentManager.beginTransaction()
            fragment = if (fragment == null || fragment is MainFragment) {
                last = 1f
                transaction
                        .setCustomAnimations(R.animator.show, 0)
                SecondFragment.newInstance()
            } else {
                transaction
                        .setCustomAnimations(0, R.animator.hide)
                MainFragment.newInstance()
            }
            transaction
                    .replace(R.id.container, fragment!!)
                    .commitNow()
        }
    }
}