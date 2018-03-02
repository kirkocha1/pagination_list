package com.task.paginationlist.domain.interactors

import com.task.paginationlist.data.db.models.WallpaperDb
import com.task.paginationlist.repositories.WallpaperRepository

import io.reactivex.Observable


class WallpaperListInteractor(val repository: WallpaperRepository) {

    val firstWallpapers: Observable<MutableList<WallpaperDb>> = repository.getListByPage(1)

    val cachedWallpapers: Observable<MutableList<WallpaperDb>> = repository.restoredList

    fun loadMore(page: Int): Observable<MutableList<WallpaperDb>> = repository.getListByPage(page)

    fun refreshList(): Observable<MutableList<WallpaperDb>> = repository.clear().flatMap { repoResult -> repository.getListByPage(1) }

    fun getWallpaperByPageAndPosition(page: Int, pos: Int) = repository.getByPageAndPos(page, pos)

}

