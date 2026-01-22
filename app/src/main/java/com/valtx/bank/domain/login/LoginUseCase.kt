package com.valtx.bank.domain.login

import com.valtx.bank.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
) {
    suspend operator fun invoke(
        username: String,
        password: String,
    ): LoginResult {
        return repository.login(username, password)
    }
}