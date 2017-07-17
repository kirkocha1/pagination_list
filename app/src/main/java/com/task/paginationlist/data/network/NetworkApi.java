package com.task.paginationlist.data.network;

import com.task.paginationlist.data.network.netmodels.WallpaperList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NetworkApi {

    @GET("wallpapers")
    Observable<WallpaperList> getWallpaperList(@Query("page") Integer pageNum);
}
