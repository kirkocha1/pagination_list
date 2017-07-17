package com.task.paginationlist.data.network;

import com.task.paginationlist.data.network.netmodels.WallpaperList;

import io.reactivex.Observable;

public class NetworkNetworkSource {

    private NetworkApi api;

    public Observable<WallpaperList> getWallpaperList(int pageNum) {
        return api.getWallpaperList(pageNum);
    }

    public NetworkNetworkSource(NetworkApi api) {
        this.api = api;
    }
}
