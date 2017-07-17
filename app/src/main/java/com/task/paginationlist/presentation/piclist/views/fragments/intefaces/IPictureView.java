package com.task.paginationlist.presentation.piclist.views.fragments.intefaces;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface IPictureView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void setImageUrl(String url);


}
