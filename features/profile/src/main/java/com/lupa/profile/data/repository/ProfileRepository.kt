package com.lupa.profile.data.repository

import com.lupa.profile.data.entity.User
import com.lupa.core.event.StateEventManager
import com.lupa.profile.data.entity.Login
import com.lupa.profile.data.remote.request.LoginRequest
import java.io.Closeable


// interface segregation
// implementasi api-impl
// api (interface) sebagai object
// impl sebagai implementasi dari api
interface ProfileRepository : Closeable {
    val userStateEventManager: StateEventManager<User>
    val loginStateEventManager: StateEventManager<Login>

    fun getUser()
    fun login(request: LoginRequest)

    fun saveToken(token:String)
}