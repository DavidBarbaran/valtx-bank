package com.valtx.bank.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class LoginResponse(
    @SerialName("data")
    val data: LoginData
)

@Serializable
data class LoginData(
    @SerialName("accessToken")
    val accessToken: String,

    @SerialName("expiresIn")
    val expiresIn: String,

    @SerialName("tokenType")
    val tokenType: String,

    @SerialName("user")
    val user: UserDto
)

@Serializable
data class UserDto(
    @SerialName("rbac")
    val rbac: RbacDto,

    @SerialName("profile")
    val profile: ProfileDto
)

@Serializable
data class RbacDto(
    @SerialName("role")
    val role: String,

    @SerialName("template")
    val template: String
)

@Serializable
data class ProfileDto(
    @SerialName("language")
    val language: String
)