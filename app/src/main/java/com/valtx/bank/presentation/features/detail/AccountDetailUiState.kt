package com.valtx.bank.presentation.features.detail

import com.valtx.bank.presentation.model.AccountDetailUi

data class AccountDetailUiState(
    val accountDetail: AccountDetailUi? = null,
    val isLoading: Boolean = false,
)