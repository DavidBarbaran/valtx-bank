package com.valtx.bank.presentation.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login_screen")
    data object Home : Screen("home_screen")
    data object AccountDetails : Screen("account_details_screen")
}