package com.task.paginationlist.presentation.piclist.presenter

import android.util.Log

import com.arellomobile.mvp.InjectViewState
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.task.paginationlist.data.db.models.WallpaperDb
import com.task.paginationlist.domain.interactors.WallpaperListInteractor
import com.task.paginationlist.presentation.piclist.views.activities.interfaces.IPicListView

import io.reactivex.Observable
import io.reactivex.ObservableTransformer

@InjectViewState
class PicListPresenter(internal var interactor: WallpaperListInteractor, internal var transformer: ObservableTransformer<List<WallpaperDb>, List<WallpaperDb>>) : BasePresenter<IPicListView>() {

    override fun onFirstViewAttach() {
        addToCompositeSubscription(interactor.firstWallpapers
                .compose(transformer)
                .subscribe({ response -> viewState.invalidateList(response) }, Consumer<Throwable> { this.handleError(it) }))
    }

    private fun handleError(throwable: Throwable) {
        var commonError = "Something wrong"
        if (throwable is HttpException) {
            commonError = "Error code: " + throwable.code() + " description: " + throwable.message
        }
        Log.e(TAG, throwable.message)
        viewState.showError(commonError)

    }

    fun loadMoreData(page: Int) {
        Log.e(TAG, "LOAD MORE DATA")
        val obs = if (page == 1) interactor.refreshList() else interactor.loadMore(page)
        addToCompositeSubscription(obs
                .compose(transformer)
                .subscribe({ response -> viewState.invalidateList(response) }, Consumer<Throwable> { this.handleError(it) })
        )
    }

    fun restoreListState(pos: Int) {
        interactor.cachedWallpapers.compose(transformer).subscribe({ list -> viewState.restoreListView(list, pos) }, Consumer<Throwable> { this.handleError(it) })
    }

    fun handleOnPostEvent(count: Int) {
        viewState.updateFullSizeCount(count)
    }

    companion object {

        val TAG = "PicListPresenter"
    }
}
