package com.valtx.bank.domain.detail

import com.valtx.bank.domain.repository.AccountDetailRepository
import com.valtx.bank.domain.result.Result
import javax.inject.Inject

class GetAccountDetailUseCase @Inject constructor(
    private val repository: AccountDetailRepository
) {
    suspend operator fun invoke(): Result<AccountDetail> {
        return repository.getAccountDetail()
    }
}