package com.valtx.bank.data.entity.detail

import com.valtx.bank.data.serializer.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class AccountDetailResponse(
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
    val amount: BigDecimal,

    @SerialName("accountNumber")
    val accountNumber: String,

    @SerialName("transactions")
    val transactions: List<TransactionResponse>
)

@Serializable
data class TransactionResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("description")
    val description: String,

    @SerialName("date")
    val date: String,

    @SerialName("currency")
    val currency: String,

    @SerialName("amount")
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal,
)