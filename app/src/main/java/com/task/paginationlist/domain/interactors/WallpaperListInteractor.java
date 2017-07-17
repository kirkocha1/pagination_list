package com.task.paginationlist.domain.interactors;

import com.task.paginationlist.data.db.models.WallpaperDb;
import com.task.paginationlist.repositories.WallpaperRepository;

import java.util.List;

import io.reactivex.Observable;


public class WallpaperListInteractor {

    private WallpaperRepository repository;

    public WallpaperListInteractor(WallpaperRepository repository) {
        this.repository = repository;
    }

    public Observable<List<WallpaperDb>> getFirstWallpapers() {
        return repository.getListByPage(1);
    }

    public Observable<List<WallpaperDb>> getCachedWallpapers() {
        return repository.getRestoredList();
    }


    public Observable<List<WallpaperDb>> loadMore(int page) {
        return repository.getListByPage(page);
    }


    public Observable<List<WallpaperDb>> refreshList() {
        return repository.clear().flatMap(repoResult -> repository.getListByPage(1));
    }

    public Observable<WallpaperDb> getWallpaperByPageAndPosition(int page, int pos) {
        return repository.getByPageAndPos(page, pos);
    }

}

