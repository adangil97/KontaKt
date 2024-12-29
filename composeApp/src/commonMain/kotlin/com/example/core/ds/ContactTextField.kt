package com.example.core.ds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    labelValue: String = "",
    msgError: String? = null,
    isError: Boolean = msgError.isNullOrBlank().not(),
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    enabled: Boolean = true,
    textStyle: TextStyle = TextStyle(fontFamily = FontFamily.SansSerif),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    endIcon: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    Column(modifier = modifier) {
        val textModifier = if (onClick != null) Modifier.clickable { onClick() } else Modifier
        OutlinedTextField(
            value,
            onValueChange = onValueChange,
            label = {
                Text(
                    labelValue,
                    maxLines = if (singleLine) 1 else Int.MAX_VALUE,
                    overflow = TextOverflow.Ellipsis
                )
            },
            leadingIcon = leadingIcon,
            isError = isError,
            trailingIcon = {
                if (isError) {
                    ContactIcon(
                        imageVector = Icons.Filled.Error,
                        tint = MaterialTheme.colors.error
                    )
                } else {
                    endIcon?.let {
                        it()
                    }
                }
            },
            enabled = enabled,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Done),
            shape = RoundedCornerShape(14.dp),
            modifier = textModifier.fillMaxWidth(),
            textStyle = textStyle
        )
        if (isError) {
            ContactSpacer(top = 4.dp)
            Text(
                textAlign = TextAlign.End,
                fontSize = 8.sp,
                text = msgError.orEmpty(),
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(start = 6.dp),
                maxLines = if (singleLine) 1 else Int.MAX_VALUE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}