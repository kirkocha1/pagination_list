package com.task.paginationlist.presentation.piclist.views.adapters.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.pic_list_item.view.*

/**
 * Created by kirill on 13.07.17.
 */

class PicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var container = itemView.picContainer

    var image: ImageView = itemView.imagePlace

    var date = itemView.imageDate

}
