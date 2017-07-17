package com.task.paginationlist.presentation.piclist.views.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.task.paginationlist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kirill on 13.07.17.
 */

public class PicViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.pic_container)
    public LinearLayout container;

    @BindView(R.id.image_place)
    public ImageView image;

    @BindView(R.id.image_date)
    public TextView date;

    public PicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
