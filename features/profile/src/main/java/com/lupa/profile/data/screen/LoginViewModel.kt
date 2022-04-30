package com.lupa.profile.data.screen

import androidx.lifecycle.ViewModel
import com.lupa.core.event.StateEventManager
import com.lupa.profile.data.entity.Login
import com.lupa.profile.data.remote.request.LoginRequest
import com.lupa.profile.data.repository.ProfileRepository

class LoginViewModel(private val repository: ProfileRepository) : ViewModel() {
    val loginEventManager: StateEventManager<Login> = repository.loginStateEventManager

    fun requestLogin(username: String, password: String) {
        val request = LoginRequest(username, password)
        repository.login(request)
    }

    fun saveToken(login: Login) {
        repository.saveToken(login.token)
    }
}