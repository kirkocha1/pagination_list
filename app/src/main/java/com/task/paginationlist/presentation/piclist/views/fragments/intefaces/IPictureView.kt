package com.task.paginationlist.presentation.piclist.views.fragments.intefaces

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


interface IPictureView : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun setImageUrl(url: String)


}
