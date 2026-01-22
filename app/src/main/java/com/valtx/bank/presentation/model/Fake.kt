package com.valtx.bank.presentation.model

import java.math.BigDecimal

val transactionsFake = listOf(
    TransactionUi(
        id = 1,
        description = "PLIN",
        date = "12 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-6.50")
    ),
    TransactionUi(
        id = 2,
        description = "Yape",
        date = "12 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-15.00")
    ),
    TransactionUi(
        id = 3,
        description = "Depósito en efectivo",
        date = "11 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("500.00")
    ),
    TransactionUi(
        id = 4,
        description = "Tambo+",
        date = "11 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-8.90")
    ),
    TransactionUi(
        id = 5,
        description = "Plaza Vea",
        date = "10 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-124.30")
    ),
    TransactionUi(
        id = 6,
        description = "Transferencia recibida",
        date = "10 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("250.00")
    ),
    TransactionUi(
        id = 7,
        description = "Uber",
        date = "09 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-23.40")
    ),
    TransactionUi(
        id = 8,
        description = "Pago de servicios - Luz",
        date = "09 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-86.70")
    ),
    TransactionUi(
        id = 9,
        description = "Interbank ATM",
        date = "08 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-200.00")
    ),
    TransactionUi(
        id = 10,
        description = "Restaurante",
        date = "08 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-45.00")
    ),
    TransactionUi(
        id = 11,
        description = "Sueldo",
        date = "07 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("3,200.00".replace(",", ""))
    ),
    TransactionUi(
        id = 12,
        description = "Netflix",
        date = "07 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-39.90")
    ),
    TransactionUi(
        id = 13,
        description = "Spotify",
        date = "06 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-18.90")
    ),
    TransactionUi(
        id = 14,
        description = "Farmacias Inkafarma",
        date = "06 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-32.50")
    ),
    TransactionUi(
        id = 15,
        description = "Recarga móvil Claro",
        date = "05 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-20.00")
    ),
    TransactionUi(
        id = 16,
        description = "Transferencia enviada",
        date = "05 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-150.00")
    ),
    TransactionUi(
        id = 17,
        description = "Pago con tarjeta",
        date = "04 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-67.80")
    ),
    TransactionUi(
        id = 18,
        description = "Depósito",
        date = "04 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("1000.00")
    ),
    TransactionUi(
        id = 19,
        description = "Cabify",
        date = "03 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-19.60")
    ),
    TransactionUi(
        id = 20,
        description = "Pago de servicios - Internet",
        date = "03 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("-129.90")
    )
)