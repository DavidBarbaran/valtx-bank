package com.valtx.bank.data.mapper

import com.valtx.bank.data.entity.product.ProductResponse
import com.valtx.bank.domain.product.Product

fun ProductResponse.toDomain() = Product(
    id = id,
    name = name,
    cardBrand = cardBrand,
    currency = currency,
    amount = amount,
    accountNumber = accountNumber
)