package com.task.paginationlist;

import android.app.Application;

import com.task.paginationlist.di.components.AppComponent;
import com.task.paginationlist.di.components.DaggerAppComponent;
import com.task.paginationlist.di.modules.RepositoryModule;

public class PaginationListApplication extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildAppComponent();
    }

    private AppComponent buildAppComponent() {
        return DaggerAppComponent.builder().repositoryModule(new RepositoryModule(this)).build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
