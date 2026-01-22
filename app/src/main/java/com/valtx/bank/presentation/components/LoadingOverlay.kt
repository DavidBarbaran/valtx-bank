package com.valtx.bank.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.valtx.bank.presentation.theme.BlackOpacity50

@Composable
fun LoadingOverlay() {
    Box(
        Modifier
            .fillMaxSize()
            .background(BlackOpacity50)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {},
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}