package com.example.core.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ContactDivider(modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .height(10.dp)
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.5f))
    )
}