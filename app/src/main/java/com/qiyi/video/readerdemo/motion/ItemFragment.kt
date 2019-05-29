package com.qiyi.video.readerdemo.motion

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.qiyi.video.readerdemo.R

class ItemFragment : Fragment() {

    companion object {
        fun newInstance() = ItemFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.item_layout, container, false)
    }

    private lateinit var myHolder: CustomAdapter.ViewHolder

    fun update(holder: CustomAdapter.ViewHolder) {
        myHolder = holder
        view?.findViewById<TextView>(R.id.txtTitle)?.text = holder.txtTitle.text
        view?.findViewById<TextView>(R.id.txtName)?.text = holder.txtName.text
    }

    override fun onStart() {
        super.onStart()
        if (this::myHolder.isInitialized) {
            update(myHolder)
        }
    }
}