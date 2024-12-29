package com.example.core.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.camera_permission_is_denied
import org.jetbrains.compose.resources.stringResource

@Composable
fun PermissionDenied(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(color = MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ContactIcon(
            imageVector = Icons.Filled.Warning,
            tint = MaterialTheme.colors.onBackground,
        )
        ContactSpacer(16.dp)
        ContactText(
            stringResource(Res.string.camera_permission_is_denied),
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
        )
    }
}