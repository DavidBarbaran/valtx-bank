package com.valtx.bank.presentation.features.login

sealed interface LoginEvent {
    data object LoginSuccess : LoginEvent
    data class ShowError(val message: String) : LoginEvent
}