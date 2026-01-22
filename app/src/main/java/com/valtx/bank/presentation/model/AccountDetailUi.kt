package com.valtx.bank.presentation.model

import java.math.BigDecimal

data class AccountDetailUi (
    val id: Int,
    val name: String,
    val cardBrand: CardBrand,
    val currency: Currency,
    val amount: BigDecimal,
    val accountNumber: String,
    val transactions: List<TransactionUi>
)