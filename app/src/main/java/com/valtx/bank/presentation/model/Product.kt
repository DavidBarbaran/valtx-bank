package com.valtx.bank.presentation.model

data class Product(
    val id: Int,
    val name: String,
    val cardBrand: CardBrand,
    val currency: Currency,
    val amount: String,
    val accountNumber: String,
)