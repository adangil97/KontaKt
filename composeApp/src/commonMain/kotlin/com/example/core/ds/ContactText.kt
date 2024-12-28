package com.example.core.ds

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun ContactText(
    value: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null,
    maxLines: Int = 1,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    modifier: Modifier = Modifier
) {
    Text(
        value,
        textAlign = textAlign,
        fontWeight = fontWeight,
        color = color,
        fontSize = fontSize,
        maxLines = maxLines,
        modifier = modifier,
        fontFamily = FontFamily.SansSerif,
        overflow = TextOverflow.Ellipsis
    )
}