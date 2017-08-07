package com.task.paginationlist.presentation.piclist.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.task.paginationlist.domain.interactors.WallpaperListInteractor;
import com.task.paginationlist.presentation.piclist.views.fragments.intefaces.IPictureView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class PicturePresenter extends BasePresenter<IPictureView> {
    public static final String TAG = "PicturePresenter";

    WallpaperListInteractor interactor;

    private int page;
    private int position;

    public PicturePresenter(WallpaperListInteractor interactor, int page, int position) {
        this.interactor = interactor;
        this.page = page;
        this.position = position;
    }

    @Override
    public void attachView(IPictureView view) {
        super.attachView(view);
        addToCompositeSubscription(interactor.getWallpaperByPageAndPosition(page, position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wallpaperDb -> {
                    getViewState().setImageUrl(wallpaperDb.getImageUrl());
                }, e -> Log.e(TAG, e.getMessage())));
    }
}
