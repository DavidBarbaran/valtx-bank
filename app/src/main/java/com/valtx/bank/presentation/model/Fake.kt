package com.valtx.bank.presentation.model

val productsFake = listOf(
    Product(
        id = 1,
        name = "Cuenta soles",
        cardBrand = CardBrand.VISA,
        currency = Currency.PEN,
        amount = "1,000.80",
        accountNumber = "898 3492915083"
    ),
    Product(
        id = 2,
        name = "Cuenta soles",
        cardBrand = CardBrand.MASTERCARD,
        currency = Currency.PEN,
        amount = "2,000.50",
        accountNumber = "898 3492915083"
    ),
    Product(
        id = 3,
        name = "Cuenta dolares",
        cardBrand = CardBrand.VISA,
        currency = Currency.USD,
        amount = "1,800.10",
        accountNumber = "545 4012915467"
    ),
    Product(
        id = 4,
        name = "Cuenta dolares",
        cardBrand = CardBrand.VISA,
        currency = Currency.USD,
        amount = "0.00",
        accountNumber = "545 4012915467"
    )
)