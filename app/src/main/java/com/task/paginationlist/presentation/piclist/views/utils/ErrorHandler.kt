package com.task.paginationlist.presentation.piclist.views.utils

import android.content.Context
import android.support.v7.app.AlertDialog


class ErrorHandler {

    fun handlerErrorMessage(context: Context, message: String) {
        AlertDialog.Builder(context)
                .setMessage(message)
                .setCancelable(true)
                .show()
    }
}
