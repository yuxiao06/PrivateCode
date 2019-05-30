package com.qiyi.video.readerdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val READER_DEMO_TAG = "ReaderDemo_"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
    }

    private fun initList() {
        val adapter = MainListAdapter(MainListDatas.datas)
        main_fun_list.setHasFixedSize(true)
        main_fun_list.layoutManager = LinearLayoutManager(this)
        main_fun_list.adapter = adapter
    }

    fun start(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}
