package com.valtx.bank.presentation.extensions

import android.content.Context
import android.content.Intent

fun Context.shareAccountNumber(accountNumber: String) {

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "Mi numero de cuenta es $accountNumber")
    }
    startActivity(Intent.createChooser(intent, "Compartir"))
}