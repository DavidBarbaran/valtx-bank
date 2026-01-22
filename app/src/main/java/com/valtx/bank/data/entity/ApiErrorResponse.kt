package com.valtx.bank.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    @SerialName("error")
    val error: ApiError
)

@Serializable
data class ApiError(
    @SerialName("code")
    val code: Int,

    @SerialName("userMessage")
    val userMessage: UserMessage?
)

@Serializable
data class UserMessage(
    @SerialName("original")
    val original: String? = null,

    @SerialName("es")
    val es: String? = null,

    @SerialName("ja")
    val ja: String? = null
)