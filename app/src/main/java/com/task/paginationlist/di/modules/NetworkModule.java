package com.task.paginationlist.di.modules

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.task.paginationlist.data.network.NetworkApi
import com.task.paginationlist.data.network.NetworkNetworkSource

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    private val baseUrl = "https://api.desktoppr.co/1/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideNetworkApi(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java!!)
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(api: NetworkApi): NetworkNetworkSource {
        return NetworkNetworkSource(api)
    }

}
