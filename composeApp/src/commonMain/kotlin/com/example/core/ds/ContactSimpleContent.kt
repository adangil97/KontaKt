package com.example.core.ds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.msg_retry
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactSimpleContent(
    msg: String,
    buttonMsg: String? = null,
    modifier: Modifier = Modifier.fillMaxSize(),
    onButtonClick: (() -> Unit)? = null,
    requireButtonClick: Boolean = onButtonClick != null,
    fontWeight: FontWeight = FontWeight.Bold,
    imageVector: ImageVector = Icons.Filled.SignalWifiOff
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContactImage(
            imageVector,
            modifier = Modifier.padding(bottom = 40.dp).size(160.dp)
        )
        ContactText(
            msg,
            fontWeight = fontWeight,
            modifier = Modifier.fillMaxWidth(.8f),
            maxLines = 2
        )
        if (requireButtonClick) {
            ContactSpacer(8.dp)
            ContactButton(buttonMsg ?: stringResource(Res.string.msg_retry)) {
                onButtonClick?.invoke()
            }
        }
    }
}