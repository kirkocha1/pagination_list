package com.task.paginationlist.presentation.piclist.views.utils

import android.content.Context

import com.task.paginationlist.R

object UtilsHelper {

    private val PHONE = "phone"

    fun isTablet(context: Context): Boolean {
        return PHONE != context.getString(R.string.device_type)
    }
}
