package com.lupa.shared.profile.di

import com.lupa.core.di.ModuleContainer
import com.lupa.shared.profile.ProfileInteractor
import com.lupa.shared.profile.ProfileInteractorImpl
import org.koin.dsl.module

class ProfileInteractorModule : ModuleContainer() {
    private val interactorModule = module {
        single<ProfileInteractor> { ProfileInteractorImpl(get()) }
    }
}