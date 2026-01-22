package com.valtx.bank.data.api

import com.valtx.bank.data.entity.LoginRequest
import com.valtx.bank.data.entity.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RestApi {

    @POST("auth/users/login/anonymous")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}