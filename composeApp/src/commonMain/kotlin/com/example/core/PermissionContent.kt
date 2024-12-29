package com.example.core

import androidx.compose.runtime.Composable

@Composable
expect fun PermissionContent(
    deniedContent: @Composable () -> Unit = {},
    content: @Composable () -> Unit
)