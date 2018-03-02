package com.task.paginationlist.repositories

import android.util.Log
import com.task.paginationlist.data.db.WallpaperDataSource
import com.task.paginationlist.data.db.models.WallpaperDb
import com.task.paginationlist.data.network.NetworkNetworkSource
import com.task.paginationlist.data.network.netmodels.WallpaperList
import com.task.paginationlist.data.utils.WallpaperMapper
import io.reactivex.Observable
import java.util.*


class WallpaperRepository(
        val cache: WallpaperDataSource,
        val network: NetworkNetworkSource,
        val mapper: WallpaperMapper
) {

    val restoredList: Observable<MutableList<WallpaperDb>>
        get() = Observable.fromCallable { cache.items }
                .filter { list -> list != null && !list.isEmpty() }
                .switchIfEmpty(network.getWallpaperList(1).map { list -> mapWallpaper(list, 1) })

    fun clear(): Observable<RepoResult> {
        return Observable.create { sub ->
            cache.deleteItems()
            sub.onNext(RepoResult(true))
        }
    }

    fun getListByPage(page: Int): Observable<MutableList<WallpaperDb>> {
        return Observable.fromCallable { cache.getItems(page) }
                .filter { list -> list != null && !list.isEmpty() }
                .switchIfEmpty(network.getWallpaperList(page).map { list -> mapWallpaper(list, page) })
    }


    private fun mapWallpaper(list: WallpaperList?, page: Int): MutableList<WallpaperDb> {
        val resultSet = ArrayList<WallpaperDb>()
        if (list != null) {
            var i = 0
            for (wallpaper in list.wallpapers!!) {
                val dModel = mapper.map(wallpaper)
                dModel.pageNum = page
                dModel.pagePosition = i
                resultSet.add(dModel)
                i++
            }
        }
        cache.putItems(resultSet)
        return cache.getItems(page)
    }

    fun getByPageAndPos(page: Int, position: Int): Observable<WallpaperDb> {
        Log.d(TAG, "getByPageAndPos: $page $position")
        return Observable.fromCallable { cache.getItem(page, position) }
    }

    companion object {

        private val TAG = "wallpaperRepository"
    }
}
