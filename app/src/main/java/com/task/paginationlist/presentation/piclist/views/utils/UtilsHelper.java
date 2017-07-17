package com.task.paginationlist.presentation.piclist.views.utils;

import android.content.Context;

import com.task.paginationlist.R;

public class UtilsHelper {

    private static final String PHONE = "phone";

    public static boolean isTablet(Context context) {
        return !PHONE.equals(context.getString(R.string.device_type));
    }
}
