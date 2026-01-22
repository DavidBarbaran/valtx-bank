package com.valtx.bank.domain.repository

import com.valtx.bank.domain.detail.AccountDetail
import com.valtx.bank.domain.result.Result

interface AccountDetailRepository {
    suspend fun getAccountDetail() : Result<AccountDetail>
}