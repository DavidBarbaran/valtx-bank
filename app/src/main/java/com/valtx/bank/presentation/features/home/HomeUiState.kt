package com.valtx.bank.presentation.features.home

import com.valtx.bank.presentation.model.ProductUi

data class HomeUiState(
    val products: List<ProductUi> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false
)