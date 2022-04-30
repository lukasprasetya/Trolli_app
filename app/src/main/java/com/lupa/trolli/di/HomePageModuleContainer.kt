package com.lupa.trolli.di

import com.lupa.core.di.ModuleContainer
import com.lupa.core.remote.RetrofitProvider
import com.lupa.profile.data.remote.ProfileWebServices
import com.lupa.profile.data.remote.sources.ProfileDataSource
import com.lupa.profile.data.repository.ProfileRepository
import com.lupa.profile.data.repository.ProfileRepositoryImpl
import com.lupa.trolli.features.screen.homepage.HomePageViewModel
import com.lupa.trolli.features.screen.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class HomePageModuleContainer  : ModuleContainer(){


    private val viewModelModule = module {
        viewModel { HomePageViewModel(get()) }
        viewModel{ SplashViewModel(get())}
    }
}