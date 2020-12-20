package com.example.kvjwenqsdp.di.module

import com.example.kvjwenqsdp.view.PopularFilmActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): PopularFilmActivity
}