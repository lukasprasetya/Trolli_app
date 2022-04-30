package com.lupa.profile.data.di

import com.lupa.core.di.ModuleContainer
import com.lupa.core.remote.RetrofitProvider
import com.lupa.profile.data.remote.ProfileWebServices
import com.lupa.profile.data.remote.sources.ProfileDataSource
import com.lupa.profile.data.repository.ProfileRepository
import com.lupa.profile.data.repository.ProfileRepositoryImpl
import com.lupa.profile.data.screen.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ProfileModuleContainer : ModuleContainer() {

    val webServiceModule = module {
        single<ProfileWebServices> {
            RetrofitProvider.retrofit().create(ProfileWebServices::class.java)
        }
    }
    val dataSourcesModule = module {
        single { ProfileDataSource(get()) }
    }

    val repositoryModule = module {
        // single<ProfileRepository> { ProfileRepositoryImpl(get()) }

        factory<ProfileRepository> { ProfileRepositoryImpl(get()) }
    }

    private val viewModelModule = module {
        viewModel { LoginViewModel(get()) }
    }
}