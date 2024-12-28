package com.example.core.ds

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ContactCard(
    elevation: TangoCardElevation = TangoCardElevation.ELEVATED,
    modifier: Modifier = Modifier,
    cornerSize: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    Card(
        elevation = elevation.value,
        shape = RoundedCornerShape(cornerSize),
        modifier = modifier,
        content = content
    )
}

enum class TangoCardElevation(val value: Dp) {
    NOT_ELEVATED(0.dp),
    ELEVATED(4.dp),
    STRONG_ELEVATED(8.dp)
}