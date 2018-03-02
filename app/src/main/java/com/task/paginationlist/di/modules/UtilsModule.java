package com.task.paginationlist.di.modules

import android.content.Context

import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.task.paginationlist.data.db.models.WallpaperDb
import com.task.paginationlist.data.utils.ILoader
import com.task.paginationlist.data.utils.ImageLoader
import com.task.paginationlist.presentation.piclist.views.utils.ErrorHandler

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient


@Module
class UtilsModule {

    @Singleton
    @Provides
    fun providePicasso(context: Context): Picasso {
        return Picasso.Builder(context).downloader(OkHttp3Downloader(OkHttpClient())).build()
    }

    @Singleton
    @Provides
    fun provideTransformer(): ObservableTransformer<List<WallpaperDb>, List<WallpaperDb>> {
        return { observable -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    }


    @Singleton
    @Provides
    fun provideImageLoader(picasso: Picasso): ILoader {
        return ImageLoader(picasso)
    }

    @Singleton
    @Provides
    fun provideErrorHandler(): ErrorHandler {
        return ErrorHandler()
    }


}
