package com.example.core.ds

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.default_image_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactEditableTag(
    msg: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = Icons.Default.Edit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(.3f)
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        imageVector?.let {
            Image(
                imageVector = it,
                contentDescription = stringResource(Res.string.default_image_description),
                modifier = Modifier.size(12.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            ContactSpacer(2.dp)
        }
        Text(msg, fontSize = 12.sp, color = Color.White)
    }
}