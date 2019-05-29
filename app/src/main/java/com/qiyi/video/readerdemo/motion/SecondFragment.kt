package com.qiyi.video.readerdemo.motion

import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qiyi.video.readerdemo.R

class SecondFragment : Fragment() {

    private lateinit var motionLayout: MotionLayout

    companion object {
        fun newInstance() = SecondFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.motion_21_second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        motionLayout = view.findViewById(R.id.main)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Log.i(SecondFragment::class.java.simpleName, "onStart of fragment...")
    }
}
