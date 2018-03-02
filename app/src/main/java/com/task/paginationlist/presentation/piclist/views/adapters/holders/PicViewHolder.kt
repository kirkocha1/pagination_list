package com.task.paginationlist.presentation.piclist.views.adapters.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.task.paginationlist.R

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by kirill on 13.07.17.
 */

class PicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.picContainer)
    var container: LinearLayout? = null

    @BindView(R.id.imagePlace)
    var image: ImageView? = null

    @BindView(R.id.imageDate)
    var date: TextView? = null

    init {
        ButterKnife.bind(this, itemView)
    }
}
