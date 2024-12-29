package com.example.core.ds

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun ContactTextButton(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    fontSize: TextUnit = TextUnit.Unspecified,
    onClick: () -> Unit
) {
    TextButton(onClick) {
        ContactText(
            value = text,
            color = MaterialTheme.colors.secondary,
            textAlign = textAlign,
            fontSize = fontSize,
            modifier = modifier
        )
    }
}