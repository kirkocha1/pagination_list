package com.task.paginationlist.presentation.piclist.views.paginator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.task.paginationlist.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoaderView extends FrameLayout {

    @BindView(R.id.progress_bar)
    public ProgressBar bar;

    @BindView(R.id.reload_btn)
    public ImageView reload;

    @BindView(R.id.error_container)
    public LinearLayout errorContainer;

    @BindView(R.id.error_message)
    public TextView error;

    private View.OnClickListener onClick;

    public LoaderView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public LoaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.loader_view, this, true);
        ButterKnife.bind(this);
        reload.setOnClickListener(v -> {
            if (onClick != null) {
                onClick.onClick(v);
                errorContainer.setVisibility(GONE);
                bar.setVisibility(VISIBLE);
            }
        });
    }

    public void setReloadClick(View.OnClickListener onClick) {
        this.onClick = onClick;
    }

    public void showError() {
        bar.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
    }

    public void showBar() {
        bar.setVisibility(VISIBLE);
    }
}
