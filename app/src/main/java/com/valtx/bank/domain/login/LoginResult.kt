package com.valtx.bank.domain.login

sealed class LoginResult {
    object Success : LoginResult()
    data class Error(val message: String) : LoginResult()
}