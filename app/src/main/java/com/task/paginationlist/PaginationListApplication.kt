package com.task.paginationlist

import android.app.Application
import com.task.paginationlist.di.components.AppComponent
import com.task.paginationlist.di.components.DaggerAppComponent
import com.task.paginationlist.di.modules.RepositoryModule

class PaginationListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        component = buildAppComponent()

    }

    private fun buildAppComponent(): AppComponent {
        return DaggerAppComponent.builder().repositoryModule(RepositoryModule(this)).build()
    }

    companion object {

        var component: AppComponent? = null
            private set
    }
}
