package com.task.paginationlist.di.components;

import android.content.Context;

import com.task.paginationlist.data.utils.ILoader;
import com.task.paginationlist.di.modules.CacheModule;
import com.task.paginationlist.di.modules.DomainModule;
import com.task.paginationlist.di.modules.NetworkModule;
import com.task.paginationlist.di.modules.RepositoryModule;
import com.task.paginationlist.di.modules.UtilsModule;
import com.task.paginationlist.presentation.piclist.presenter.PicListPresenter;
import com.task.paginationlist.presentation.piclist.presenter.PicturePresenter;
import com.task.paginationlist.presentation.piclist.views.fragments.PicGridListFragment;
import com.task.paginationlist.presentation.piclist.views.fragments.PictureFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class, UtilsModule.class, CacheModule.class, DomainModule.class})
public interface AppComponent {

    Context getContext();

    ILoader getLoader();

    void inject(PicListPresenter picListPresenter);

    void inject(PictureFragment fragment);

    void inject(PicGridListFragment picListPresenter);

    void inject(PicturePresenter picListPresenter);
}
