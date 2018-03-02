package com.task.paginationlist.presentation.piclist.views.activities.interfaces

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.task.paginationlist.data.db.models.WallpaperDb

@StateStrategyType(SkipStrategy::class)
interface IPicListView : MvpView {

    fun invalidateList(wallpapers: List<WallpaperDb>)

    fun showError(error: String)

    fun setFullSizePic(pos: Int)

    fun updateFullSizeCount(count: Int)

    fun restoreListView(restoredData: List<WallpaperDb>, pos: Int)


}
