package com.task.paginationlist.di.modules;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.task.paginationlist.data.db.models.WallpaperDb;
import com.task.paginationlist.data.utils.ILoader;
import com.task.paginationlist.data.utils.ImageLoader;
import com.task.paginationlist.presentation.piclist.views.utils.ErrorHandler;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;


@Module
public class UtilsModule {

    @Singleton
    @Provides
    public Picasso providePicasso(Context context) {
        return new Picasso.Builder(context).downloader(new OkHttp3Downloader(new OkHttpClient())).build();
    }

    @Singleton
    @Provides
    public ObservableTransformer<List<WallpaperDb>, List<WallpaperDb>> provideTransformer() {
        return observable -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    @Singleton
    @Provides
    public ILoader provideImageLoader(Picasso picasso) {
        return new ImageLoader(picasso);
    }

    @Singleton
    @Provides
    public ErrorHandler provideErrorHandler() {
        return new ErrorHandler();
    }


}
