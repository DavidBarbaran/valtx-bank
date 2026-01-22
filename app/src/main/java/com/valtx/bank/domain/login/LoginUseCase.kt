package com.valtx.bank.domain.login

import com.valtx.bank.domain.repository.LoginRepository
import com.valtx.bank.domain.result.Result
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
) {
    suspend operator fun invoke(
        username: String,
        password: String,
    ): Result<Unit> {
        return repository.login(username, password)
    }
}