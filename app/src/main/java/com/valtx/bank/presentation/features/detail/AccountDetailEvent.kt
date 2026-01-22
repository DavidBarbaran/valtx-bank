package com.valtx.bank.presentation.features.detail

sealed interface AccountDetailEvent {
    data class ShowError(val message: String) : AccountDetailEvent
}