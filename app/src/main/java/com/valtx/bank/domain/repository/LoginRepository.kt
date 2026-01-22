package com.valtx.bank.domain.repository

import com.valtx.bank.domain.login.LoginResult

interface LoginRepository {
    suspend fun login(document: String, password: String): LoginResult
}