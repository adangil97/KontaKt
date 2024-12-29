package com.example.core

import androidx.compose.runtime.Composable

@Composable
actual fun PermissionContent(
    deniedContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    content()
}