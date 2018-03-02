package com.task.paginationlist.di.modules

import com.task.paginationlist.domain.interactors.WallpaperListInteractor
import com.task.paginationlist.repositories.WallpaperRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideWallpaperInteractor(repository: WallpaperRepository): WallpaperListInteractor {
        return WallpaperListInteractor(repository)
    }
}
