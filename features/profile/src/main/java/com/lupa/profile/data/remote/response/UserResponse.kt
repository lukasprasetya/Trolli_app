package com.lupa.profile.data.remote.response


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("city")
    val city: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("simple_address")
    val simpleAddress: String,
    @SerializedName("username")
    val username: String
)