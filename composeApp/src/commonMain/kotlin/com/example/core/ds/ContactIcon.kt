package com.example.core.ds

import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.default_image_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactIcon(
    imageVector: ImageVector,
    contentDescription: String = stringResource(Res.string.default_image_description),
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}