package com.task.paginationlist.presentation.piclist.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.task.paginationlist.data.db.models.WallpaperDb;
import com.task.paginationlist.domain.interactors.WallpaperListInteractor;
import com.task.paginationlist.presentation.piclist.views.activities.interfaces.IPicListView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

@InjectViewState
public class PicListPresenter extends BasePresenter<IPicListView> {

    public static final String TAG = "PicListPresenter";

    ObservableTransformer<List<WallpaperDb>, List<WallpaperDb>> transformer;

    WallpaperListInteractor interactor;

    public PicListPresenter(WallpaperListInteractor interactor, ObservableTransformer<List<WallpaperDb>, List<WallpaperDb>> transformer) {
        this.transformer = transformer;
        this.interactor = interactor;
    }

    @Override
    protected void onFirstViewAttach() {
        addToCompositeSubscription(interactor.getFirstWallpapers()
                .compose(transformer)
                .subscribe(response -> {
                    getViewState().invalidateList(response);
                }, this::handleError));
    }

    private void handleError(Throwable throwable) {
        String commonError = "Something wrong";
        if (throwable instanceof HttpException) {
            HttpException httpEr = (HttpException) throwable;
            commonError = "Error code: " + httpEr.code() + " description: " + httpEr.getMessage();
        }
        Log.e(TAG, throwable.getMessage());
        getViewState().showError(commonError);

    }

    public void loadMoreData(int page) {
        Log.e(TAG, "LOAD MORE DATA");
        Observable<List<WallpaperDb>> obs = page == 1 ? interactor.refreshList() : interactor.loadMore(page);
        addToCompositeSubscription(obs
                .compose(transformer)
                .subscribe(response -> getViewState().invalidateList(response), this::handleError)
        );
    }

    public void restoreListState(int pos) {
        interactor.getCachedWallpapers().compose(transformer).subscribe(list -> {
            getViewState().restoreListView(list, pos);
        }, this::handleError);
    }

    public void handleOnPostEvent(int count) {
        getViewState().updateFullSizeCount(count);
    }
}
