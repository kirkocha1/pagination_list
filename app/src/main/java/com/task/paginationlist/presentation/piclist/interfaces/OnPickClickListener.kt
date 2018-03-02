package com.task.paginationlist.presentation.piclist.interfaces

import android.view.View


interface OnPickClickListener {
    fun onPickClick(v: View, position: Int, id: Int, size: Int)
}
