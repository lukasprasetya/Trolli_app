package com.lupa.trolli.features.screen.splash

import androidx.lifecycle.ViewModel
import com.lupa.core.event.StateEventManager
import com.lupa.profile.data.entity.User
import com.lupa.profile.data.repository.ProfileRepository
import okhttp3.internal.closeQuietly

class SplashViewModel(private val profileRepository: ProfileRepository): ViewModel() {

    val userManager: StateEventManager<User> = profileRepository.userStateEventManager

    fun getUser(){
        profileRepository.getUser()
    }

    override fun onCleared() {
        super.onCleared()
        profileRepository.close()
        userManager.closeQuietly()
    }
}