package com.valtx.bank.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.valtx.bank.R
import com.valtx.bank.presentation.model.Product
import com.valtx.bank.presentation.model.productsFake
import com.valtx.bank.presentation.theme.ValtxBankTheme
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavController) {

    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    val state = rememberPullToRefreshState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = stringResource(R.string.home_title),
            style = MaterialTheme.typography.displayMedium
        )

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
            },
            state = state,
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = isRefreshing,
                    state = state
                )
            },
            modifier = Modifier
                .fillMaxSize()

        ) {

            ProductList(
                modifier = Modifier,
                products = productsFake
            )
        }
    }

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            delay(1200)
            isRefreshing = false
        }
    }
}

@Composable
fun ProductList(modifier: Modifier, products: List<Product>) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            vertical = 16.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductItem(
                product = product,
                onClick = {}
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ValtxBankTheme {
        HomeScreen(rememberNavController())
    }
}