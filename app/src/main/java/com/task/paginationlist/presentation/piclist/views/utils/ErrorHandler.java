package com.task.paginationlist.presentation.piclist.views.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;


public class ErrorHandler {

    public void handlerErrorMessage(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setCancelable(true)
                .show();
    }
}
