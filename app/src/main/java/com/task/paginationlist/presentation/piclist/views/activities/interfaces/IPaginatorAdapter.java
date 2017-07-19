package com.task.paginationlist.presentation.piclist.views.activities.interfaces;

import android.view.View;

import com.task.paginationlist.presentation.piclist.interfaces.OnPickClickListener;

public interface IPaginatorAdapter {

    void setSuccessful(boolean successful);

    int getRealItemCount();

    void setReload(View.OnClickListener listener);

    void setOnPicClick(OnPickClickListener onPicClick);

    void setFooterVisibility(boolean isAllLoaded);
}
