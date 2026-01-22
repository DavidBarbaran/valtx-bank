package com.valtx.bank.presentation.model

enum class CardBrand {
    VISA,
    MASTERCARD;

    companion object {
        fun fromText(value: String): CardBrand = runCatching {
            CardBrand.valueOf(value.uppercase())
        }.getOrDefault(VISA)
    }
}