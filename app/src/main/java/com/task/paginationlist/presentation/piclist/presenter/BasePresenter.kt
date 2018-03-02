package com.task.paginationlist.presentation.piclist.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    private val compositeSubscription = CompositeDisposable()

    protected fun addToCompositeSubscription(subscription: Disposable) {
        compositeSubscription.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscription.clear()
    }
}
