package com.example.kvjwenqsdp.di.module

import android.app.Application
import android.content.Context
import com.example.kvjwenqsdp.reository.network.NetworkTmdbRepository
import com.example.kvjwenqsdp.reository.network.NetworkTmdbRepositoryImpl
import com.example.kvjwenqsdp.reository.network.api.TmdbService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    fun provideTmdbRepository(service:TmdbService): NetworkTmdbRepositoryImpl {
        return NetworkTmdbRepositoryImpl(service)
    }
}