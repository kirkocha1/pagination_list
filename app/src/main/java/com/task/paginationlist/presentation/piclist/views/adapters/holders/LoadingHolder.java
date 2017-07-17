package com.task.paginationlist.presentation.piclist.views.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.task.paginationlist.R;
import com.task.paginationlist.presentation.piclist.views.paginator.LoaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.footer_list_item)
    public LoaderView loaderView;

    public LoadingHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
