package com.valtx.bank.domain.detail

import java.math.BigDecimal

data class Transaction(
    val id: Int,
    val description: String,
    val date: String,
    val currency: String,
    val amount: BigDecimal,
)