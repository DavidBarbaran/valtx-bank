package com.valtx.bank.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.valtx.bank.R
import com.valtx.bank.presentation.components.PrimaryButton
import com.valtx.bank.presentation.extensions.icon
import com.valtx.bank.presentation.home.ProductItem
import com.valtx.bank.presentation.model.CardBrand
import com.valtx.bank.presentation.model.Currency
import com.valtx.bank.presentation.model.Product
import com.valtx.bank.presentation.model.Transaction
import com.valtx.bank.presentation.model.transactionsFake
import com.valtx.bank.presentation.theme.ValtxBankTheme

@Composable
fun AccountDetailScreen(navController: NavController) {

    val product = remember {
        Product(
            id = 1,
            name = "Cuenta soles",
            cardBrand = CardBrand.VISA,
            currency = Currency.PEN,
            amount = "1,000.80",
            accountNumber = "898 3492915083"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        AccountDetailTopBar(
            navController = navController
        )

        ProductCard(
            product = product
        )

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 16.dp),
            text = stringResource(R.string.account_detail_movements),
            style = MaterialTheme.typography.titleMedium
        )

        TransactionList(
            transactions = transactionsFake
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailTopBar(
    navController: NavController,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.account_detail_title),
                style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
            state = rememberTopAppBarState()
        )
    )
}

@Composable
fun ProductCard(
    product: Product,
) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
    ) {

        Row(
            modifier = Modifier.padding(20.dp)
        ) {
            Icon(
                painter = product.cardBrand.icon(),
                contentDescription = product.cardBrand.name,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Top),
                tint = Color.Unspecified,
            )

            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.name,
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${product.currency.symbol} ${product.amount}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.account_detail_account_number),
                )

                Text(
                    modifier = Modifier
                        .wrapContentWidth(),
                    text = product.accountNumber,
                    style = MaterialTheme.typography.bodyMedium
                )

                PrimaryButton(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(36.dp),
                    text = stringResource(R.string.account_detail_share)
                ) {

                }
            }
        }
    }
}

@Composable
fun TransactionList(
    transactions: List<Transaction>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = 16.dp,
            start = 20.dp,
            end = 20.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(
            items = transactions,
            key = { it.id }
        ) { transaction ->
            TransactionItem(
                transaction = transaction,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AccountDetailScreenPreview() {
    ValtxBankTheme {
        AccountDetailScreen(rememberNavController())
    }
}