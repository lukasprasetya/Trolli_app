package com.lupa.profile.data.remote.sources

import com.lupa.profile.data.ProfileMapper
import com.lupa.profile.data.remote.ProfileWebServices
import com.lupa.profile.data.entity.User
import com.lupa.core.util.mapObservable
import com.lupa.profile.data.entity.Login
import com.lupa.profile.data.remote.request.LoginRequest
import io.reactivex.Observable

class ProfileDataSource(private val profileWebServices: ProfileWebServices) {

    fun getUser(): Observable<User> {
        println("get user data sources...")
        return profileWebServices.getUser().mapObservable { response ->
            println("mapper....")
            ProfileMapper.mapUserResponseToEntity(response)
        }
    }

    fun postLogin(request: LoginRequest): Observable<Login> {
        return profileWebServices.postLogin(request).mapObservable {
            ProfileMapper.mapLoginResponseToEntity(it)
        }
    }
}