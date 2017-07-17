package com.task.paginationlist.presentation.piclist.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.task.paginationlist.PaginationListApplication;
import com.task.paginationlist.R;
import com.task.paginationlist.data.db.models.WallpaperDb;
import com.task.paginationlist.presentation.piclist.interfaces.ILoadMoreData;
import com.task.paginationlist.presentation.piclist.presenter.PicListPresenter;
import com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity;
import com.task.paginationlist.presentation.piclist.views.activities.interfaces.IFullSizeViewCreator;
import com.task.paginationlist.presentation.piclist.views.activities.interfaces.IPicListView;
import com.task.paginationlist.presentation.piclist.views.fragments.base.BaseGridListFragment;
import com.task.paginationlist.presentation.piclist.views.utils.ErrorHandler;
import com.task.paginationlist.presentation.piclist.views.utils.UtilsHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity.PIC_COUNT;
import static com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity.PIC_ID;
import static com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity.POSITON_PIC;


public class PicGridListFragment extends BaseGridListFragment implements IPicListView {

    public static final String RESTORE_LAST_VISIBLE_ITEM_POS = "restore_last_visible_item_pos";
    public static final String RESTORE_INIT = "restore_init";


    @InjectPresenter
    PicListPresenter presenter;

    @Inject
    ErrorHandler handler;

    IFullSizeViewCreator creator;

    boolean isInit = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PaginationListApplication.getComponent().inject(this);
        View view = inflater.inflate(R.layout.picture_list_frafment, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPicClickListener((v, pos, id, count) -> {
            if (!UtilsHelper.isTablet(getContext())) {
                Intent intent = new Intent(getContext(), FullSizeContainerActivity.class);
                intent.putExtra(POSITON_PIC, pos);
                intent.putExtra(PIC_ID, id);
                intent.putExtra(PIC_COUNT, count);
                startActivity(intent);
            } else {
                setFullSizePic(pos);
            }
        });
    }

    @Override
    public void invalidateList(List<WallpaperDb> wallpapers) {
        if (UtilsHelper.isTablet(getContext()) && isInit) {
            creator.createFullSizePicFragment();
            isInit = false;
        }
        onLoadFinished(wallpapers);
    }

    @Override
    public ILoadMoreData getDataLoader() {
        return presenter;
    }

    @Override
    public void showError(String error) {
        if (getAdapter() != null) {
            getAdapter().setSuccessful(false);
        }
        refreshView.setRefreshing(false);
        refreshView.setEnabled(true);
        handler.handlerErrorMessage(getContext(), error);
    }

    @Override
    public void setFullSizePic(int pos) {
        FullSizePicFragment fragment = (FullSizePicFragment) getFragmentManager().findFragmentByTag(getString(R.string.full_size_fragment_tag));
        if (fragment != null) {
            fragment.setCurrentPic(pos);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(RESTORE_INIT, isInit);
        outState.putInt(RESTORE_LAST_VISIBLE_ITEM_POS, getLastListItemPosition());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            isInit = savedInstanceState.getBoolean(RESTORE_INIT);
            presenter.restoreListState(savedInstanceState.getInt(RESTORE_LAST_VISIBLE_ITEM_POS));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFullSizeViewCreator) {
            creator = (IFullSizeViewCreator) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        creator = null;
    }

    @Override
    public void restoreListView(List<WallpaperDb> restoredData, int lastItemPosition) {
        setLastVisiblePosition(lastItemPosition);
        onLoadFinished(restoredData);
    }

    @Override
    public void updateFullSizeCount(int count) {
        if (UtilsHelper.isTablet(getContext())) {
            FullSizePicFragment fragment = (FullSizePicFragment) getFragmentManager().findFragmentByTag(getString(R.string.full_size_fragment_tag));
            if (fragment != null) {
                fragment.updateCount(count);
            }
        }
    }
}
