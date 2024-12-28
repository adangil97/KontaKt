package com.example.core.ds

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ContactButton(
    text: String,
    enabled: Boolean = true,
    cornerRadius: Dp = 14.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    isError: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.primary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isError) MaterialTheme.colors.error else backgroundColor
        ),
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        Text(
            text,
            modifier = Modifier.padding(
                top = 6.dp,
                bottom = 6.dp
            ),
            maxLines = 1
        )
    }
}