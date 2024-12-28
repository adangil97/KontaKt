package com.example.core.ds

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.default_image_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactImage(
    imageBitmap: ImageBitmap,
    contentDescription: String = stringResource(Res.string.default_image_description),
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    editableContent: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
            .clickable(onClick = onClick)
    ) {
        Image(
            bitmap = imageBitmap,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
        editableContent?.let {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                it()
            }
        }
    }
}

@Composable
fun ContactImage(
    imageVector: ImageVector,
    contentDescription: String = stringResource(Res.string.default_image_description),
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {
    Image(
        imageVector = imageVector,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}