package com.valtx.bank.domain.repository

import com.valtx.bank.domain.result.Result

interface LoginRepository {
    suspend fun login(document: String, password: String): Result<Unit>
}