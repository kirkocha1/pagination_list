package com.task.paginationlist.data.network

import com.task.paginationlist.data.network.netmodels.WallpaperList

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkApi {

    @GET("wallpapers")
    fun getWallpaperList(@Query("page") pageNum: Int?): Observable<WallpaperList>
}
