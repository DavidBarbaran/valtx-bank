package com.valtx.bank.presentation.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valtx.bank.presentation.extensions.icon
import com.valtx.bank.presentation.model.CardBrand
import com.valtx.bank.presentation.model.Currency
import com.valtx.bank.presentation.model.Product
import com.valtx.bank.presentation.theme.ValtxBankTheme

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {

        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = product.cardBrand.icon(),
                contentDescription = product.cardBrand.name,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically),
                tint = Color.Unspecified,
            )

            Column {

                Text(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 20.dp)
                        .fillMaxWidth(),
                    text = product.name,
                )

                Text(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 20.dp)
                        .fillMaxWidth(),
                    text = "${product.currency.symbol} ${product.amount}",
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    val product = Product(
        id = 1,
        name = "Cuenta soles",
        cardBrand = CardBrand.VISA,
        currency = Currency.PEN,
        amount = "1,000.80",
        accountNumber = "898 3492915083"
    )
    ValtxBankTheme {
        ProductItem(
            product = product,
            onClick = {}
        )
    }
}