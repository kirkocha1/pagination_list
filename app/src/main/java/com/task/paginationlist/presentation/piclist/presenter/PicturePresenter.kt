package com.task.paginationlist.presentation.piclist.presenter

import android.util.Log

import com.arellomobile.mvp.InjectViewState
import com.task.paginationlist.domain.interactors.WallpaperListInteractor
import com.task.paginationlist.presentation.piclist.views.fragments.intefaces.IPictureView

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@InjectViewState
class PicturePresenter(
        private var interactor: WallpaperListInteractor,
        private val page: Int,
        private val position: Int
) : BasePresenter<IPictureView>() {

    override fun attachView(view: IPictureView) {
        super.attachView(view)
        addToCompositeSubscription(interactor.getWallpaperByPageAndPosition(page, position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ wallpaperDb -> viewState.setImageUrl(wallpaperDb.imageUrl!!) }) { e -> Log.e(TAG, e.message) })
    }

    companion object {
        val TAG = "PicturePresenter"
    }
}
