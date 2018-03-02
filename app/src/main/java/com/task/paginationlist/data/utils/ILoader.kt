package com.task.paginationlist.data.utils

import android.widget.ImageView

/**
 * Created by kirill on 11.07.17.
 */

interface ILoader {
    fun loadImage(url: String, imageView: ImageView)
}
