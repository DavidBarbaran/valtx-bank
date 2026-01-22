package com.valtx.bank.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valtx.bank.presentation.theme.Red

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Red),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 12.dp,
            disabledElevation = 0.dp
        ),
        contentPadding = PaddingValues(start = 40.dp, end = 40.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium
        )
    }
}


@Composable
fun PrimaryTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = text,
            color = Red,
            style = MaterialTheme.typography.labelMedium
        )
    }
}