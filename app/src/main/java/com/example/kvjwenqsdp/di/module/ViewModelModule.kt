package com.example.kvjwenqsdp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kvjwenqsdp.di.scope.ViewModelKey
import com.example.kvjwenqsdp.viewmodel.PopularFilmActViewModel
import com.example.kvjwenqsdp.viewmodel.PopularFilmDetailViewModel
import com.example.kvjwenqsdp.viewmodel.PopularFilmViewModel
import com.yenen.ahmet.basecorelibrary.base.di.factory.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularFilmViewModel::class)
    internal abstract fun bindPopularFilmViewModel(viewModel: PopularFilmViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularFilmDetailViewModel::class)
    internal abstract fun bindPopularFilmDetailViewModel(viewModel: PopularFilmDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularFilmActViewModel::class)
    internal abstract fun bindPopularFilmActViewModel(viewModel: PopularFilmActViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory

}