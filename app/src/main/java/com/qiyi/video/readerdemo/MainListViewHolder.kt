package com.qiyi.video.readerdemo

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.widget.TextView

class MainListViewHolder(var layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
    var title = layout.findViewById(R.id.title) as TextView
    var description = layout.findViewById(R.id.description) as TextView
    var activity : Class<*>? = null

    init {
        layout.setOnClickListener {
            val context = it?.context as MainActivity
            activity?.let {
                context.start(it)
            }
        }
    }
}