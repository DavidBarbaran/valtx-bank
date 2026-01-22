package com.valtx.bank.presentation.features.home

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.valtx.bank.R
import com.valtx.bank.presentation.components.BasicDialog
import com.valtx.bank.presentation.components.LoadingOverlay
import com.valtx.bank.presentation.model.ProductUi
import com.valtx.bank.presentation.navigation.Screen
import com.valtx.bank.presentation.theme.ValtxBankTheme

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val homeUiState by viewModel.uiState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var dialogText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {

        viewModel.getProducts()

        viewModel.events.collect { event ->
            when (event) {
                is HomeEvent.ShowError -> {
                    dialogText = event.message
                    showDialog = true
                }
            }
        }
    }

    HomeScreenUI(
        navController = navController,
        products = homeUiState.products,
        isRefreshing = homeUiState.isRefreshing,
        onRefresh = {
            viewModel.refreshProducts()
        }
    )

    if (showDialog) {
        BasicDialog(
            textContent = dialogText,
            textButton = stringResource(R.string.retry),
            onDismiss = {
                showDialog = false
                viewModel.getProducts()
            }
        )
    }

    if (homeUiState.isLoading) {
        LoadingOverlay()
    }
}

@Composable
fun HomeScreenUI(
    navController: NavController,
    products: List<ProductUi>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
) {
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
            onRefresh = onRefresh,
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
                products = products,
                onClick = {
                    navController.navigate(Screen.AccountDetails.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun ProductList(
    modifier: Modifier,
    products: List<ProductUi>,
    onClick: () -> Unit,
) {
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
                onClick = onClick
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(
    @PreviewParameter(ProductsPreviewProvider::class)
    products: List<ProductUi>,
) {
    ValtxBankTheme {
        HomeScreenUI(
            navController = rememberNavController(),
            products = products,
            isRefreshing = false,
            onRefresh = {}
        )
    }
}