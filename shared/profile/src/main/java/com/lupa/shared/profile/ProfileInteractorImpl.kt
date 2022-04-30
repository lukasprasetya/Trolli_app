package com.lupa.shared.profile

import com.lupa.core.event.StateEvent
import com.lupa.core.event.StateEventManager
import com.lupa.profile.data.entity.User
import com.lupa.profile.data.repository.ProfileRepository
import com.lupa.shared.profile.ProfileInteractor

class ProfileInteractorImpl(private val profileRepository: ProfileRepository) : ProfileInteractor {

    override fun isUserLogon(): Boolean {
        println("repository -> $profileRepository")
        profileRepository.getUser()
        val isLogon = userStateEventManager.value is StateEvent.Success
        println("isLogon -> $isLogon")
        return isLogon
    }

    override val userStateEventManager: StateEventManager<User>
        get() = profileRepository.userStateEventManager
}