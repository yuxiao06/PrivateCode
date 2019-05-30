package com.qiyi.video.readerdemo.motion.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qiyi.video.readerdemo.R
import com.qiyi.video.readerdemo.motion.fragment.MainFragment


class MotionMainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.motion_21_main_fragment, container, false)
    }
}
