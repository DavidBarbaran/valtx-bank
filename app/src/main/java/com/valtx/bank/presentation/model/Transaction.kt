package com.valtx.bank.presentation.model

import java.math.BigDecimal

data class Transaction(
    val id: Int,
    val description: String,
    val date: String,
    val currency: Currency,
    val amount: BigDecimal
)