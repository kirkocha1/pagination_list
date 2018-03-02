package com.task.paginationlist.presentation.piclist.views.adapters.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.task.paginationlist.presentation.piclist.views.paginator.LoaderView
import kotlinx.android.synthetic.main.footer_item.view.*

class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var loaderView: LoaderView? = itemView.footerListItem

}
