package com.valtx.bank.presentation.model

import java.math.BigDecimal

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

val transactionsFake = listOf(
    Transaction(
        id = 1,
        description = "PLIN",
        date = "12 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-6.50")
    ),
    Transaction(
        id = 2,
        description = "Yape",
        date = "12 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-15.00")
    ),
    Transaction(
        id = 3,
        description = "Depósito en efectivo",
        date = "11 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("500.00")
    ),
    Transaction(
        id = 4,
        description = "Tambo+",
        date = "11 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-8.90")
    ),
    Transaction(
        id = 5,
        description = "Plaza Vea",
        date = "10 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-124.30")
    ),
    Transaction(
        id = 6,
        description = "Transferencia recibida",
        date = "10 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("250.00")
    ),
    Transaction(
        id = 7,
        description = "Uber",
        date = "09 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-23.40")
    ),
    Transaction(
        id = 8,
        description = "Pago de servicios - Luz",
        date = "09 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-86.70")
    ),
    Transaction(
        id = 9,
        description = "Interbank ATM",
        date = "08 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-200.00")
    ),
    Transaction(
        id = 10,
        description = "Restaurante",
        date = "08 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-45.00")
    ),
    Transaction(
        id = 11,
        description = "Sueldo",
        date = "07 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("3,200.00".replace(",", ""))
    ),
    Transaction(
        id = 12,
        description = "Netflix",
        date = "07 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-39.90")
    ),
    Transaction(
        id = 13,
        description = "Spotify",
        date = "06 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-18.90")
    ),
    Transaction(
        id = 14,
        description = "Farmacias Inkafarma",
        date = "06 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-32.50")
    ),
    Transaction(
        id = 15,
        description = "Recarga móvil Claro",
        date = "05 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-20.00")
    ),
    Transaction(
        id = 16,
        description = "Transferencia enviada",
        date = "05 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-150.00")
    ),
    Transaction(
        id = 17,
        description = "Pago con tarjeta",
        date = "04 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-67.80")
    ),
    Transaction(
        id = 18,
        description = "Depósito",
        date = "04 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("1000.00")
    ),
    Transaction(
        id = 19,
        description = "Cabify",
        date = "03 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-19.60")
    ),
    Transaction(
        id = 20,
        description = "Pago de servicios - Internet",
        date = "03 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-129.90")
    )
)