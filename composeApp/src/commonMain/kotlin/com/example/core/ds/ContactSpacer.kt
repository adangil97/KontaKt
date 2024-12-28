package com.example.core.ds

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ContactSpacer(space: Dp = 0.dp) {
    Spacer(modifier = Modifier.padding(space))
}