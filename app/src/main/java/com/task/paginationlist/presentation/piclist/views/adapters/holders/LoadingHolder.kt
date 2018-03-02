package com.task.paginationlist.presentation.piclist.views.adapters.holders

import android.support.v7.widget.RecyclerView
import android.view.View

import com.task.paginationlist.R
import com.task.paginationlist.presentation.piclist.views.paginator.LoaderView

import butterknife.BindView
import butterknife.ButterKnife

class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.footer_list_item)
    var loaderView: LoaderView? = null

    init {
        ButterKnife.bind(this, itemView)
    }
}
