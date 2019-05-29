package com.qiyi.video.readerdemo

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class MainListAdapter (private val dataset: Array<MainListData>) :
        RecyclerView.Adapter<MainListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        val row = LayoutInflater.from(parent.context)
                .inflate(R.layout.row, parent, false) as ConstraintLayout
        return MainListViewHolder(row)
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        holder.title.text = dataset[position].title
        holder.description.text = dataset[position].description
        holder.activity = dataset[position].activity
    }

    override fun getItemCount() = dataset.size
}