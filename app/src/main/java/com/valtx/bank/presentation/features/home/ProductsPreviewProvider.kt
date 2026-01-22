package com.valtx.bank.presentation.features.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.valtx.bank.presentation.model.CardBrand
import com.valtx.bank.presentation.model.Currency
import com.valtx.bank.presentation.model.ProductUi
import java.math.BigDecimal

class ProductsPreviewProvider : PreviewParameterProvider<List<ProductUi>> {

    override val values: Sequence<List<ProductUi>> = sequenceOf(
        listOf(
            ProductUi(
                id = 1,
                name = "Cuenta soles",
                cardBrand = CardBrand.VISA,
                currency = Currency.PEN,
                amount = BigDecimal("1,000.80"),
                accountNumber = "898 3492915083"
            ),
            ProductUi(
                id = 2,
                name = "Cuenta soles",
                cardBrand = CardBrand.MASTERCARD,
                currency = Currency.PEN,
                amount = BigDecimal("2,000.50"),
                accountNumber = "898 3492915083"
            ),
            ProductUi(
                id = 3,
                name = "Cuenta dolares",
                cardBrand = CardBrand.VISA,
                currency = Currency.USD,
                amount = BigDecimal("1,800.10"),
                accountNumber = "545 4012915467"
            ),
            ProductUi(
                id = 4,
                name = "Cuenta dolares",
                cardBrand = CardBrand.VISA,
                currency = Currency.USD,
                amount = BigDecimal("0.00"),
                accountNumber = "545 4012915467"
            )
        )
    )
}