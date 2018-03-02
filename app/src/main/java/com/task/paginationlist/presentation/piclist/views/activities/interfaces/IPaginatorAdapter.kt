package com.task.paginationlist.presentation.piclist.views.activities.interfaces

import android.view.View

import com.task.paginationlist.presentation.piclist.interfaces.OnPickClickListener

interface IPaginatorAdapter {

    val realItemCount: Int

    fun setSuccessful(successful: Boolean)

    fun setReload(listener: View.OnClickListener)

    fun setOnPicClick(onPicClick: OnPickClickListener)

    fun setFooterVisibility(isAllLoaded: Boolean)
}
