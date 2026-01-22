package com.valtx.bank.data.api

import com.valtx.bank.data.entity.login.LoginRequest
import com.valtx.bank.data.entity.login.LoginResponse
import com.valtx.bank.data.entity.product.ProductResponse
import com.valtx.bank.data.entity.detail.AccountDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestApi {

    @POST("auth/users/login/anonymous")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("accounts")
    suspend fun getProducts(): Response<List<ProductResponse>>

    @GET("details")
    suspend fun getAccountDetail(): Response<AccountDetailResponse>
}