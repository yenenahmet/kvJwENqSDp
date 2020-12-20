package com.example.kvjwenqsdp.di.module

import com.example.kvjwenqsdp.view.PopularFilmDetailFragment
import com.example.kvjwenqsdp.view.PopularFilmFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributePopularFilmFragment(): PopularFilmFragment

    @ContributesAndroidInjector
    abstract fun contributePopularFilmDetailFragment(): PopularFilmDetailFragment
}