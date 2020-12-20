package com.example.kvjwenqsdp.di.module


import com.example.kvjwenqsdp.reository.network.api.TmdbService
import com.example.kvjwenqsdp.utils.ProjectUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }


    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient {
        val builder=  OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        return builder.build()
    }


    @Provides
    @Singleton
    internal fun provideRetrofitService(okHttpClient: OkHttpClient, gson: Gson): TmdbService {
        val retrofit =  Retrofit.Builder()
            .baseUrl(ProjectUtil.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient).build()
        return retrofit.create(TmdbService::class.java)
    }



}