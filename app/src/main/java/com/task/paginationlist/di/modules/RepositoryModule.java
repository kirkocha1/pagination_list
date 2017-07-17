package com.task.paginationlist.di.modules;

import android.content.Context;

import com.task.paginationlist.data.db.WallpaperDataSource;
import com.task.paginationlist.data.network.NetworkNetworkSource;
import com.task.paginationlist.data.utils.WallpaperMapper;
import com.task.paginationlist.repositories.WallpaperRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    private Context context;

    public RepositoryModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }


    @Singleton
    @Provides
    public WallpaperMapper provideWallpaperMapper() {
        return new WallpaperMapper();
    }


    @Singleton
    @Provides
    public WallpaperRepository provideWallpaperRepository(WallpaperDataSource cache, NetworkNetworkSource network, WallpaperMapper mapper) {
        return new WallpaperRepository(cache, network, mapper);
    }
}
