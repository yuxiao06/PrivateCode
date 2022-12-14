package com.qiyi.video.readerdemo.motion.adapter

import android.graphics.Rect
import android.support.constraint.motion.MotionLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.qiyi.video.readerdemo.R
import com.qiyi.video.readerdemo.motion.fragment.ItemFragment
import com.qiyi.video.readerdemo.motion.User

class CustomAdapter(private val userList: ArrayList<User>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = userList[position].name
        holder.txtTitle.text = userList[position].title
        holder.itemView.setOnClickListener {
            val parent = it?.parent?.parent?.parent?.parent
            if (parent is MotionLayout) {
                val offsetViewBounds = Rect()
                it.getDrawingRect(offsetViewBounds)
                parent.offsetDescendantRectToMyCoords(it, offsetViewBounds)
                val transaction = (it.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                val fragment = ItemFragment.newInstance()
                fragment.update(holder)
                transaction.replace(R.id.container, fragment)
                transaction.commitNow()
                parent.transitionToEnd()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById(R.id.txtName) as TextView
        val txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
    }

}