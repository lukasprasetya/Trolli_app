package com.lupa.shared.profile

import com.lupa.core.event.StateEventManager
import com.lupa.profile.data.entity.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

interface ProfileInteractor {

    fun isUserLogon(): Boolean
    val userStateEventManager: StateEventManager<User>

    companion object : KoinComponent {
        val get: ProfileInteractor = get()
    }
}