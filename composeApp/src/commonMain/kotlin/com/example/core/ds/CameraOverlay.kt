package com.example.core.ds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CameraOverlay(
    isCapturing: Boolean,
    onCapture: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        IconButton(
            onClick = onBack,
            modifier =
            Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp, start = 16.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Back Button",
                tint = Color.White,
            )
        }
        if (isCapturing) {
            CircularProgressIndicator(
                modifier =
                Modifier
                    .size(80.dp)
                    .align(Alignment.Center),
                color = Color.White.copy(alpha = 0.7f),
                strokeWidth = 8.dp,
            )
        }
        InstagramCameraButton(
            modifier =
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            onClick = onCapture,
        )
    }
}