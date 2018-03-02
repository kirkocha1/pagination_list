package com.task.paginationlist.di.modules

import android.arch.persistence.room.Room
import android.content.Context

import com.task.paginationlist.data.db.AppDb
import com.task.paginationlist.data.db.WallpaperDataSource
import com.task.paginationlist.data.db.dao.WallpaperDao

import javax.inject.Singleton

import dagger.Module
import dagger.Provides


@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideDb(context: Context): AppDb {
        return Room.databaseBuilder<AppDb>(context, AppDb::class.java!!, "pagination_list.db").build()
    }

    @Singleton
    @Provides
    fun provideWallpaperDao(db: AppDb): WallpaperDao {
        return db.wallpaperDao
    }


    @Singleton
    @Provides
    fun provideCacheDataSource(dao: WallpaperDao): WallpaperDataSource {
        return WallpaperDataSource(dao)
    }

}
