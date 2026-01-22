package com.valtx.bank.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valtx.bank.presentation.model.Currency
import com.valtx.bank.presentation.model.Transaction
import com.valtx.bank.presentation.theme.Green
import com.valtx.bank.presentation.theme.Red
import com.valtx.bank.presentation.theme.ValtxBankTheme
import java.math.BigDecimal

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {

    val amountColor = if (transaction.amount < BigDecimal.ZERO) Red else Green

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.fillMaxWidth(),
    ) {

        Row (
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = transaction.description,
                )

                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = "${transaction.currency.symbol} ${transaction.amount}",
                style = MaterialTheme.typography.bodyLarge,
                color = amountColor
            )
        }
    }
}

@Preview
@Composable
fun TransactionItemPreview() {

    val transaction = Transaction(
        id = 1,
        description = "PLIN",
        date = "12 Ene 2025",
        currency = Currency.PEN,
        amount = BigDecimal("6.50")
    )

    ValtxBankTheme {
        TransactionItem(transaction = transaction)
    }
}