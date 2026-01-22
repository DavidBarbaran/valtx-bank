package com.valtx.bank.presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.valtx.bank.R
import com.valtx.bank.presentation.model.CardBrand

@Composable
fun CardBrand.icon(): Painter =
    when (this) {
        CardBrand.VISA -> painterResource(R.drawable.ic_visa)
        CardBrand.MASTERCARD -> painterResource(R.drawable.ic_mastercard)
    }