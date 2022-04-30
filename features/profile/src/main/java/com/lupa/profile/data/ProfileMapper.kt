package com.lupa.profile.data

import com.lupa.profile.data.entity.Login
import com.lupa.profile.data.entity.User
import com.lupa.profile.data.remote.response.LoginResponse
import com.lupa.profile.data.remote.response.UserResponse

object ProfileMapper {
    fun mapUserResponseToEntity(userResponse: UserResponse?): User {
        return User(
            city = userResponse?.city.orEmpty(),
            fullName = userResponse?.fullName.orEmpty(),
            id = userResponse?.id.orEmpty(),
            imageUrl = userResponse?.imageUrl.orEmpty(),
            role = userResponse?.role.orEmpty(),
            simpleAddress = userResponse?.simpleAddress.orEmpty(),
            username = userResponse?.username.orEmpty()
        )
    }

    fun mapLoginResponseToEntity(loginResponse: LoginResponse?):Login {
        return Login(token = loginResponse?.token.orEmpty())
    }
}