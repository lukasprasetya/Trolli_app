package com.lupa.core.remote

import com.google.gson.annotations.SerializedName

data class BaseResponse <T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: T? = null,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)