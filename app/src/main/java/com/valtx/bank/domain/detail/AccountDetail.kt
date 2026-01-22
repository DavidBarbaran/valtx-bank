package com.valtx.bank.domain.detail

import java.math.BigDecimal

data class AccountDetail(
    val id: Int,
    val name: String,
    val cardBrand: String,
    val currency: String,
    val amount: BigDecimal,
    val accountNumber: String,
    val transactions: List<Transaction>
)