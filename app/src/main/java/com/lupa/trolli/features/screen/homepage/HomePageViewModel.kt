package com.lupa.trolli.features.screen.homepage

import androidx.lifecycle.ViewModel
import com.lupa.profile.data.entity.User
import com.lupa.core.event.StateEventManager
import com.lupa.profile.data.repository.ProfileRepository
import okhttp3.internal.closeQuietly

class HomePageViewModel(private val repository: ProfileRepository) : ViewModel() {

/*    private val service = WebServices.create()
    private val dataSource = UserDataSource(service)
    private val repository: UserRepository=UserRepositoryImpl(dataSource)*/

    val userManager: StateEventManager<User> = repository.userStateEventManager

    fun getUser(){
        repository.getUser()
    }

    override fun onCleared() {
        super.onCleared()
        repository.close()
        userManager.closeQuietly()
    }
}