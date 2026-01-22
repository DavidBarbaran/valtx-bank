package com.valtx.bank.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.valtx.bank.presentation.home.HomeScreen
import com.valtx.bank.presentation.login.LoginScreen
import com.valtx.bank.presentation.navigation.Screen
import com.valtx.bank.presentation.theme.ValtxBankTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ValtxBankTheme {
                MainNavHost()
            }
        }
    }
}

@Composable
fun MainNavHost() {
    val rootNavController = rememberNavController()

    NavHost(rootNavController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(rootNavController)
        }
        composable(Screen.Home.route) {
            HomeScreen(rootNavController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ValtxBankTheme {
        MainNavHost()
    }
}