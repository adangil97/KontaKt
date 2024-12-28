package com.example.core.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.hideKeyboard
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.default_image_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactSearchBar(
    hint: String,
    initialText: String = "",
    modifier: Modifier = Modifier,
    height: Dp = 40.dp,
    elevation: Dp = 3.dp,
    cornerShape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = Color.White,
    onSearch: (String, Boolean) -> Unit = { _, _ -> },
) {
    var isEnabled by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(initialText) }
    var active by remember { mutableStateOf(false) }
    val keyboard = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier
            .height(height)
            .shadow(elevation = elevation, shape = cornerShape)
            .background(color = backgroundColor, shape = cornerShape)
            .clickable {
                isEnabled = true
                active = true
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .focusRequester(focusRequester),
            value = text,
            onValueChange = {
                active = it.isNotBlank()
                text = it
                if (active) {
                    onSearch(text, true)
                }
            },
            enabled = isEnabled,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            decorationBox = { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        color = Color.Gray.copy(alpha = 0.5f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(text, false)
                    hideKeyboard()
                    keyboard?.hide()
                    focusManager.clearFocus()
                    active = false
                    isEnabled = false
                }
            ),
            singleLine = true
        )
        LaunchedEffect(isEnabled) {
            focusRequester.requestFocus()
        }
        val imageDescription = stringResource(Res.string.default_image_description)
        if (active) {
            Icon(
                modifier = modifier
                    .clickable {
                        if (text.isEmpty()) {
                            onSearch(text, false)
                            hideKeyboard()
                            keyboard?.hide()
                            focusManager.clearFocus()
                            active = false
                            isEnabled = false
                        } else {
                            text = ""
                        }
                    }
                    .size(40.dp)
                    .padding(10.dp)
                    .weight(1f),
                imageVector = Icons.Filled.Clear,
                contentDescription = imageDescription,
                tint = MaterialTheme.colors.primary,
            )
        } else {
            Icon(
                modifier = modifier
                    .clickable {
                        active = true
                    }
                    .fillMaxSize()
                    .padding(10.dp)
                    .weight(1f),
                imageVector = Icons.Filled.Search,
                contentDescription = imageDescription,
                tint = MaterialTheme.colors.primary,
            )
        }
    }
}