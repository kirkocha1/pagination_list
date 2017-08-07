package com.task.paginationlist.presentation.piclist.views.paginator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.task.paginationlist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kirill on 07.08.17.
 */

public class PaginatorList extends FrameLayout {

    @BindView(R.id.pic_list)
    RecyclerView list;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private LinearLayoutManager manager;

    private boolean isAllLoaded;
    private boolean isLoading;
    private boolean isError = false;
    private OnLoadMoreListener listener;

    public PaginatorList(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PaginatorList(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setManager(LinearLayoutManager manager) {
        this.manager = manager;
        list.setLayoutManager(manager);
    }

    public void setLoadMoreListener(OnLoadMoreListener listener) {
        this.listener = listener;
    }

    public void setAllLoaded(boolean allLoaded) {
        isAllLoaded = allLoaded;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        list.setAdapter(adapter);
    }

    public void onCompleteLoad() {
        swipeRefresh.setRefreshing(false);
        swipeRefresh.setEnabled(true);
        isLoading = false;
        isError = false;
    }

    public void onErrorLoad() {
        onCompleteLoad();
        isError = true;
    }

    public boolean isRefreshing() {
        return swipeRefresh.isRefreshing();
    }

    public int getLastListItemPosition() {
        return manager != null ? manager.findLastVisibleItemPosition() : 0;
    }

    public void setLastVisiblePosition(int position) {
        if (manager != null) {
            list.post(() -> manager.scrollToPosition(position));
        }
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.paginator_list_view, this, true);
        ButterKnife.bind(this);
        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (manager != null && list.getAdapter() != null) {
                    int visibleItemCount = manager.getChildCount();
                    int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (!isAllLoaded && !isError && !isLoading && list.getAdapter().getItemCount() != 1) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {//list.getAdapter().getItemCount()) {
                            isLoading = true;
                            if (listener != null) {
                                listener.loadMoreData(list.getAdapter().getItemCount() / Config.LIMIT + 1);
                            }
                        }
                    }
                }
            }
        });

        swipeRefresh.setOnRefreshListener(() -> {
            swipeRefresh.setEnabled(false);
            isAllLoaded = false;
            isError = false;
            if (listener != null) {
                listener.loadMoreData(1);
            }
        });
    }

    public interface OnLoadMoreListener {
        void loadMoreData(int page);
    }
}
