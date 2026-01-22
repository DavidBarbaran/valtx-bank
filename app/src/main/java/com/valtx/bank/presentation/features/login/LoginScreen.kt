package com.valtx.bank.presentation.features.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.valtx.bank.R
import com.valtx.bank.presentation.components.BasicDialog
import com.valtx.bank.presentation.components.LoadingOverlay
import com.valtx.bank.presentation.components.PrimaryButton
import com.valtx.bank.presentation.components.PrimaryTextButton
import com.valtx.bank.presentation.navigation.Screen
import com.valtx.bank.presentation.theme.ValtxBankTheme
import kotlin.text.isDigit

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val loginUiState by viewModel.uiState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var dialogText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {

        viewModel.events.collect { event ->
            when (event) {

                LoginEvent.LoginSuccess -> {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }

                is LoginEvent.ShowError -> {
                    dialogText = event.message
                    showDialog = true
                }
            }
        }
    }

    LoginScreenUI(
        onLogin = { document, password ->
            viewModel.login(document, password)
        }
    )

    if (showDialog) {
        BasicDialog(
            textContent = dialogText,
            textButton = "Aceptar",
            onDismiss = { showDialog = false }
        )
    }

    if (loginUiState.isLoading) {
        LoadingOverlay()
    }
}

@Composable
fun LoginScreenUI(onLogin: (String, String) -> Unit) {

    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_coin))

    var document by rememberSaveable { mutableStateOf("") }

    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(180.dp)
                .scale(1.2f),
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever,
        )

        Text(
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.displayLarge
        )

        Text(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            text = stringResource(R.string.login_description),
            textAlign = TextAlign.Center
        )

        DocumentTextField(
            value = document,
            onValueChange = { newValue -> document = newValue }
        )

        PasswordTextField(
            value = password,
            onValueChange = { newValue -> password = newValue }
        )

        PrimaryButton(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 24.dp),
            text = stringResource(R.string.login_get_started),
            onClick = {
                onLogin(document, password)
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryTextButton(
            text = stringResource(R.string.login_forgot_password),
            onClick = {}
        )
    }
}

@Composable
fun DocumentTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {

    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.all { it.isDigit() } && newValue.length <= 8) {
                onValueChange(newValue)
            }
        },
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .height(56.dp)
            .fillMaxWidth(),
        placeholder = {
            Text(
                stringResource(R.string.login_document),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { onValueChange("") },
                    tint = Color.Unspecified
                )
            }
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = RoundedCornerShape(12.dp),
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= 20) onValueChange(newValue)
        },
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .height(56.dp)
            .fillMaxWidth(),
        placeholder = {
            Text(
                stringResource(R.string.login_password),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val iconRes = if (passwordVisible)
                R.drawable.ic_password
            else
                R.drawable.ic_password_hidden

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = RoundedCornerShape(12.dp),
    )
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    ValtxBankTheme {
        LoginScreenUI(onLogin = { document, password -> })
    }
}