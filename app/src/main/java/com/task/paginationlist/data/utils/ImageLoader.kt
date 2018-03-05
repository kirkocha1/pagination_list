package com.task.paginationlist.data.utils

import android.widget.ImageView

import com.squareup.picasso.Picasso
import com.task.paginationlist.R

/**
 * Created by kirill on 11.07.17.
 */

class ImageLoader(private val picasso: Picasso) : ILoader {

    override fun loadImage(url: String, imageView: ImageView) {
        picasso.load(url).placeholder(R.drawable.image_placeholder).into(imageView)
    }
}

