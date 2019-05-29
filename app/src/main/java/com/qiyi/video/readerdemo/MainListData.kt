package com.qiyi.video.readerdemo

data class MainListData (val title: String, val description : String, val layout : Int = 0, val activity : Class<*>) {
    constructor(title: String, description: String, activity : Class<*>) : this(title, description, 0, activity)
}