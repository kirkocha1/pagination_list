package com.task.paginationlist.data.network

import com.task.paginationlist.data.network.netmodels.WallpaperList

import io.reactivex.Observable

class NetworkNetworkSource(private val api: NetworkApi) {

    fun getWallpaperList(pageNum: Int): Observable<WallpaperList> {
        return api.getWallpaperList(pageNum)
    }
}
