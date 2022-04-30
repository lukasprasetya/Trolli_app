package com.lupa.core.di

import com.lupa.core.local.DataPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class CoreModuleContainer: ModuleContainer() {

    private val preferencesModule = module{
        single{ DataPreferences(androidContext())}
    }
}