package com.lupa.profile.data.remote

import com.lupa.core.remote.TroliResponse
import com.lupa.profile.data.remote.request.LoginRequest
import com.lupa.profile.data.remote.response.LoginResponse
import com.lupa.profile.data.remote.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ProfileWebServices {
// https://aurel-store.herokuapp.com/v1/user


    @GET(EndPoint.User.GET_USER)
    fun getUser(
        // @Header("Authorization") auth: String = TOKEN_SAMPLE
    ): TroliResponse<UserResponse>

    @POST(EndPoint.User.POST_LOGIN)
    fun postLogin(
        @Body request: LoginRequest
    ): TroliResponse<LoginResponse>

    /*companion object {
        private const val TOKEN_SAMPLE =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6ImNvbS5hZWouYXVyZWwiLCJpZCI6ImQyZTQ2MjdjLTU5MjgtNDJhZi05YTQzLTk5YjRkMzYyNTBlYiIsImV4cCI6MTY0ODI3OTMzMSwiaGFzaCI6InJKUExyT0ZOWHdtdE1XcUtWV1R6UUE9PSJ9._kHwmebd6tYd08DwISg1E0c4Fl2Hb0fzOyRyHGu52GIh0uhb_wM8wYoLtO8DuCLctuS4AxFzp7svFU2NAdOgMw"
    }*/

    object EndPoint {
        // const val BASE_URL = "https://aurel-store.herokuapp.com/v1/"

        object User {
            const val GET_USER = "user"
            const val POST_LOGIN = "user/login"
        }
    }
}