package com.valtx.bank.presentation.mapper

import com.valtx.bank.domain.product.Product
import com.valtx.bank.presentation.model.CardBrand
import com.valtx.bank.presentation.model.Currency
import com.valtx.bank.presentation.model.ProductUi

fun Product.toUi() = ProductUi(
    id = id,
    name = name,
    cardBrand = CardBrand.fromText(cardBrand),
    currency = Currency.fromText(currency),
    amount = amount,
    accountNumber = accountNumber
)