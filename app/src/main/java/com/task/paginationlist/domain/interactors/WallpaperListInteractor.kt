package com.task.paginationlist.domain.interactors

import com.task.paginationlist.repositories.WallpaperRepository


class WallpaperListInteractor(private val repository: WallpaperRepository) {

    fun firstWallpapers() = repository.getListByPage(1)

    fun cachedWallpapers() = repository.restoredList()

    fun loadMore(page: Int) = repository.getListByPage(page)

    fun refreshList() = repository.clear().flatMap { repoResult -> repository.getListByPage(1) }

    fun getWallpaperByPageAndPosition(page: Int, pos: Int) = repository.getByPageAndPos(page, pos)

}

