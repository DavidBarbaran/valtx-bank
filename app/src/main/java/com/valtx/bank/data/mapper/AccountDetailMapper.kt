package com.valtx.bank.data.mapper

import com.valtx.bank.data.entity.detail.AccountDetailResponse
import com.valtx.bank.data.entity.detail.TransactionResponse
import com.valtx.bank.domain.detail.AccountDetail
import com.valtx.bank.domain.detail.Transaction

fun AccountDetailResponse.toDomain() = AccountDetail(
    id = id,
    name = name,
    cardBrand = cardBrand,
    currency = currency,
    amount = amount,
    accountNumber = accountNumber,
    transactions = transactions.map { it.toDomain() }
)

fun TransactionResponse.toDomain() = Transaction(
    id = id,
    description = description,
    date = date,
    currency = currency,
    amount = amount,
)