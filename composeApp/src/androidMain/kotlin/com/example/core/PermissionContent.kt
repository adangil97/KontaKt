package com.example.core

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
actual fun PermissionContent(
    deniedContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val permissionsState = rememberMultiplePermissionsState(listOf(Manifest.permission.CAMERA))

    LaunchedEffect(Unit) {
        permissionsState.launchMultiplePermissionRequest()
    }
    if (permissionsState.allPermissionsGranted) {
        content()
    } else {
        deniedContent()
    }
}