package com.lupa.core.remote

import com.lupa.core.local.DataPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val preferences = DataPreferences.get
        val token = preferences.token

        val request = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", token)
            .build()

        return chain.proceed(request)
    }
}