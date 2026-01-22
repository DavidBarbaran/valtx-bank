package com.valtx.bank.presentation.features.detail

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.valtx.bank.R
import com.valtx.bank.presentation.components.DialogAlert
import com.valtx.bank.presentation.components.LoadingOverlay
import com.valtx.bank.presentation.components.PrimaryButton
import com.valtx.bank.presentation.extensions.icon
import com.valtx.bank.presentation.extensions.shareAccountNumber
import com.valtx.bank.presentation.model.AccountDetailUi
import com.valtx.bank.presentation.model.TransactionUi
import com.valtx.bank.presentation.theme.ValtxBankTheme

@Composable
fun AccountDetailScreen(
    navController: NavController,
    viewModel: AccountDetailViewModel = hiltViewModel(),
) {

    val accountDetailUiState by viewModel.uiState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var dialogText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {

        viewModel.getAccountDetail()

        viewModel.events.collect { event ->
            when (event) {
                is AccountDetailEvent.ShowError -> {
                    dialogText = event.message
                    showDialog = true
                }
            }
        }
    }

    accountDetailUiState.accountDetail?.let { accountDetail ->
        AccountDetailScreenUI(
            navController = navController,
            accountDetail = accountDetail
        )
    }

    if (showDialog) {
        DialogAlert(
            textContent = dialogText,
            textButton = stringResource(R.string.retry),
            onDismiss = {
                showDialog = false
                viewModel.getAccountDetail()
            }
        )
    }

    if (accountDetailUiState.isLoading) {
        LoadingOverlay()
    }
}

@Composable
fun AccountDetailScreenUI(
    navController: NavController,
    accountDetail: AccountDetailUi,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        AccountDetailTopBar(
            navController = navController
        )

        ProductCard(
            accountDetail = accountDetail
        )

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 16.dp),
            text = stringResource(R.string.account_detail_movements),
            style = MaterialTheme.typography.titleMedium
        )

        TransactionList(
            transactions = accountDetail.transactions
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
    accountDetail: AccountDetailUi,
) {

    val context = LocalContext.current

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
                painter = accountDetail.cardBrand.icon(),
                contentDescription = accountDetail.cardBrand.name,
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
                    text = accountDetail.name,
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${accountDetail.currency.symbol} ${accountDetail.amount}",
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
                    text = accountDetail.accountNumber,
                    style = MaterialTheme.typography.bodyMedium
                )

                PrimaryButton(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(36.dp),
                    text = stringResource(R.string.account_detail_share)
                ) {
                    context.shareAccountNumber(accountDetail.accountNumber)
                }
            }
        }
    }
}

@Composable
fun TransactionList(
    transactions: List<TransactionUi>,
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
fun AccountDetailScreenPreview(
    @PreviewParameter(AccountDetailPreviewProvider::class)
    accountDetail: AccountDetailUi,
) {
    ValtxBankTheme {
        AccountDetailScreenUI(
            navController = rememberNavController(),
            accountDetail = accountDetail
        )
    }
}