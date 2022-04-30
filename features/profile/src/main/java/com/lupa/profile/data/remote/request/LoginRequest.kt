package com.lupa.profile.data.remote.request


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("password")
    var password: String? = null
)