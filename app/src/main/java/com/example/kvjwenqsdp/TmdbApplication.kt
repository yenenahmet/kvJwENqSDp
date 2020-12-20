package com.example.kvjwenqsdp

import com.example.kvjwenqsdp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TmdbApplication :DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }


    private val appComponent = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

}