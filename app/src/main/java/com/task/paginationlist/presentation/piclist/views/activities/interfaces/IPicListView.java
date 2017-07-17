package com.task.paginationlist.presentation.piclist.views.activities.interfaces;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.task.paginationlist.data.db.models.WallpaperDb;

import java.util.List;

@StateStrategyType(SkipStrategy.class)
public interface IPicListView extends MvpView {

    void invalidateList(List<WallpaperDb> wallpapers);

    void showError(String error);

    void setFullSizePic(int pos);

    void updateFullSizeCount(int count);

    void restoreListView(List<WallpaperDb> restoredData, int pos);


}
