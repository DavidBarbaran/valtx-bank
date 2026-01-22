package com.valtx.bank.data.entity.product

import com.valtx.bank.data.serializer.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ProductResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("cardBrand")
    val cardBrand: String,

    @SerialName("currency")
    val currency: String,

    @SerialName("amount")
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal
)