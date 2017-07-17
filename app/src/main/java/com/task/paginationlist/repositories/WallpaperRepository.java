package com.task.paginationlist.repositories;

import android.util.Log;

import com.task.paginationlist.data.db.WallpaperDataSource;
import com.task.paginationlist.data.db.models.WallpaperDb;
import com.task.paginationlist.data.network.NetworkNetworkSource;
import com.task.paginationlist.data.network.netmodels.Wallpaper;
import com.task.paginationlist.data.network.netmodels.WallpaperList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


public class WallpaperRepository {

    private static final String TAG = "wallpaperRepository";
    private WallpaperDataSource cache;
    private NetworkNetworkSource network;

    public WallpaperRepository(WallpaperDataSource cache, NetworkNetworkSource network) {
        this.cache = cache;
        this.network = network;
    }

    public Observable<RepoResult> clear() {
        return Observable.create(sub -> {
            cache.deleteItems();
            sub.onNext(new RepoResult(true));
        });
    }

    public Observable<List<WallpaperDb>> getListByPage(final int page) {
        return Observable.fromCallable(() -> cache.getItems(page))
                .filter(list -> list != null && !list.isEmpty())
                .switchIfEmpty(network.getWallpaperList(page).map(list -> mapWallpaper(list, page)));
    }


    public Observable<List<WallpaperDb>> getRestoredList() {
        return Observable.fromCallable(() -> cache.getItems())
                .filter(list -> list != null && !list.isEmpty())
                .switchIfEmpty(network.getWallpaperList(1).map(list -> mapWallpaper(list, 1)));
    }


    private List<WallpaperDb> mapWallpaper(WallpaperList list, int page) {
        List<WallpaperDb> resultSet = new ArrayList<>();
        if (list != null) {
            int i = 0;
            for (Wallpaper wallpaper : list.wallpapers) {
                WallpaperDb dModel = wallpaper.toWallpaperDb();
                dModel.setPageNum(page);
                dModel.setPagePosition(i);
                resultSet.add(dModel);
                i++;
            }
        }
        cache.putItems(resultSet);
        return cache.getItems(page);
    }

    public Observable<WallpaperDb> getByPageAndPos(int page, int position) {
        Log.d(TAG, "getByPageAndPos: " + page + " " + position);
        return Observable.fromCallable(() -> cache.getItem(page, position));
    }
}
