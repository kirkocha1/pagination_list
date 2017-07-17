package com.task.paginationlist.di.modules;

import com.task.paginationlist.domain.interactors.WallpaperListInteractor;
import com.task.paginationlist.repositories.WallpaperRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {

    @Singleton
    @Provides
    public WallpaperListInteractor provideWallpaperInteractor(WallpaperRepository repository) {
        return new WallpaperListInteractor(repository);
    }
}
