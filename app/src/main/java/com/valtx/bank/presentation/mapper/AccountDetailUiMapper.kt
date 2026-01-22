package com.valtx.bank.presentation.mapper

import com.valtx.bank.domain.detail.AccountDetail
import com.valtx.bank.domain.detail.Transaction
import com.valtx.bank.presentation.model.AccountDetailUi
import com.valtx.bank.presentation.model.CardBrand
import com.valtx.bank.presentation.model.Currency
import com.valtx.bank.presentation.model.TransactionUi

fun AccountDetail.toUi() = AccountDetailUi(
    id = id,
    name = name,
    cardBrand = CardBrand.fromText(cardBrand),
    currency = Currency.fromText(currency),
    amount = amount,
    accountNumber = accountNumber,
    transactions = transactions.map { it.toUi() }
)

fun Transaction.toUi() = TransactionUi(
    id = id,
    description = description,
    date = date,
    currency = Currency.fromText(currency),
    amount = amount,
)