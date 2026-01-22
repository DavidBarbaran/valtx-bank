package com.valtx.bank.domain.product

import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val cardBrand: String,
    val currency: String,
    val amount: BigDecimal
)