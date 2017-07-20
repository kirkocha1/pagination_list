package com.task.paginationlist.presentation.piclist.views.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.task.paginationlist.R;
import com.task.paginationlist.data.db.models.WallpaperDb;
import com.task.paginationlist.presentation.piclist.interfaces.ILoadMoreData;
import com.task.paginationlist.presentation.piclist.interfaces.OnPickClickListener;
import com.task.paginationlist.presentation.piclist.views.activities.interfaces.IPaginatorAdapter;
import com.task.paginationlist.presentation.piclist.views.adapters.PicAdapter;
import com.task.paginationlist.presentation.piclist.views.paginator.Config;

import java.util.List;

import butterknife.BindView;

public abstract class BaseGridListFragment extends MvpAppCompatFragment {

    protected boolean isLoading = false;
    protected boolean isAllLoaded = false;

    @BindView(R.id.pic_list)
    protected RecyclerView list;

    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout refreshView;

    protected PicAdapter adapter;
    protected GridLayoutManager manager;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        adapter = new PicAdapter();
        adapter.setReload(v -> getDataLoader().loadMoreData(0));

        list.setAdapter(adapter);
        manager = new GridLayoutManager(getContext(), Config.GRID_ROWS_COUNT);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case PicAdapter.ITEM_TYPE:
                        return 1;
                    case PicAdapter.FOOTER_TYPE:
                        return Config.GRID_ROWS_COUNT;
                    default:
                        return -1;
                }
            }
        });
        list.setLayoutManager(manager);
        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = manager.getChildCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                if (!isAllLoaded && !isLoading && adapter.getItemCount() != 1) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {//list.getAdapter().getItemCount()) {
                        isLoading = true;
                        getDataLoader().loadMoreData(list.getAdapter().getItemCount() / Config.LIMIT + 1);
                    }
                }
            }
        });
        refreshView.setOnRefreshListener(() -> {
            refreshView.setEnabled(false);
            isAllLoaded = false;
            getDataLoader().loadMoreData(1);
        });
    }

    public void setPicClickListener(OnPickClickListener listener) {
        if (adapter != null) {
            adapter.setOnPicClick(listener);
        }
    }

    public IPaginatorAdapter getAdapter() {
        return adapter;
    }

    public int getLastListItemPosition() {
        return manager != null ? manager.findLastVisibleItemPosition() : 0;
    }

    public void setLastVisiblePosition(int position) {
        if (manager != null) {
            list.post(() -> manager.scrollToPosition(position));
        }
    }

    public abstract ILoadMoreData getDataLoader();

    public void onLoadFinished(List<WallpaperDb> data) {
        isAllLoaded = data == null || data.size() < Config.LIMIT;
        list.post(() -> {
            adapter.setFooterVisibility(isAllLoaded);
            adapter.addData(data, refreshView.isRefreshing());
            refreshView.setRefreshing(false);
            refreshView.setEnabled(true);
            isLoading = false;
            getDataLoader().handleOnPostEvent(adapter.getItemCount() - 1);
        });
    }

}
