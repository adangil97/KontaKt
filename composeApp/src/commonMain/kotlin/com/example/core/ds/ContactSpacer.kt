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

@Composable
fun ContactSpacer(
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp
) {
    Spacer(modifier = Modifier.padding(start, top, end, bottom))
}