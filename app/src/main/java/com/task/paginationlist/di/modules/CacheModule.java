package com.task.paginationlist.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.task.paginationlist.data.db.AppDb;
import com.task.paginationlist.data.db.WallpaperDataSource;
import com.task.paginationlist.data.db.dao.WallpaperDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class CacheModule {

    @Singleton
    @Provides
    public AppDb provideDb(Context context) {
        return Room.databaseBuilder(context, AppDb.class, "pagination_list.db").build();
    }

    @Singleton
    @Provides
    public WallpaperDao provideWallpaperDao(AppDb db) {
        return db.getWallpaperDao();
    }


    @Singleton
    @Provides
    public WallpaperDataSource provideCacheDataSource(WallpaperDao dao) {
        return new WallpaperDataSource(dao);
    }

}
