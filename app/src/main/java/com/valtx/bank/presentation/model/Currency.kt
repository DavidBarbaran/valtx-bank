package com.valtx.bank.presentation.model

enum class Currency(val symbol: String) {
    PEN("S/"),
    USD("$");

    companion object {
        fun fromText(value: String): Currency = runCatching {
            Currency.valueOf(value.uppercase())
        }.getOrDefault(PEN)
    }
}