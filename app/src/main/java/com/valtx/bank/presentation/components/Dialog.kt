package com.valtx.bank.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.valtx.bank.presentation.theme.ValtxBankTheme

@Composable
fun BasicDialog(
    textContent: String,
    textButton: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = textContent,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                PrimaryTextButton(
                    text = textButton,
                    onClick = onDismiss
                )
            }
        },
        onDismissRequest = {},
    )
}

@Preview
@Composable
fun PreviewAboutDialog() {
    ValtxBankTheme {
        BasicDialog(
            textContent = "Ocurrio un error inesperado",
            textButton = "Reintentar",
            onDismiss = {}
        )
    }
}
