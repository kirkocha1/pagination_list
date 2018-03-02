package com.task.paginationlist.domain.interactors

import com.task.paginationlist.data.db.models.WallpaperDb
import com.task.paginationlist.repositories.WallpaperRepository

import io.reactivex.Observable


class WallpaperListInteractor(private val repository: WallpaperRepository) {

    val firstWallpapers: Observable<List<WallpaperDb>>
        get() = repository.getListByPage(1)

    val cachedWallpapers: Observable<List<WallpaperDb>>
        get() = repository.restoredList


    fun loadMore(page: Int): Observable<List<WallpaperDb>> {
        return repository.getListByPage(page)
    }


    fun refreshList(): Observable<List<WallpaperDb>> {
        return repository.clear().flatMap { repoResult -> repository.getListByPage(1) }
    }

    fun getWallpaperByPageAndPosition(page: Int, pos: Int): Observable<WallpaperDb> {
        return repository.getByPageAndPos(page, pos)
    }

}

