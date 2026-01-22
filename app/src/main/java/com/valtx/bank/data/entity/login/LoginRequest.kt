package com.valtx.bank.data.entity.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("user")
    val user: UserRequest,

    @SerialName("device")
    val device: DeviceRequest,

    @SerialName("app")
    val app: AppRequest
)

@Serializable
data class UserRequest(
    @SerialName("usr_code")
    val userCode: String,

    @SerialName("pass")
    val pass: String,

    @SerialName("profile")
    val profile: ProfileRequest
)

@Serializable
data class ProfileRequest(
    @SerialName("language")
    val language: String
)

@Serializable
data class DeviceRequest(
    @SerialName("deviceId")
    val deviceId: String,

    @SerialName("name")
    val name: String,

    @SerialName("version")
    val version: String,

    @SerialName("width")
    val width: String,

    @SerialName("height")
    val height: String,

    @SerialName("model")
    val model: String,

    @SerialName("platform")
    val platform: String
)

@Serializable
data class AppRequest(
    @SerialName("version")
    val version: String
)