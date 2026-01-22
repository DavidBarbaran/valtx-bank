package com.valtx.bank.presentation.features.home

sealed interface HomeEvent {
    data class ShowError(val message: String) : HomeEvent
}