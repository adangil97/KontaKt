package com.example.core.ds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.select_from_camera
import kontakt.composeapp.generated.resources.select_from_gallery
import kontakt.composeapp.generated.resources.select_image
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactSelectorBottomSheet(
    modifier: Modifier = Modifier,
    onGallerySelection: () -> Unit,
    onCameraSelection: () -> Unit,
    onClose: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ContactBottomSheet(
        modifier = modifier,
        onClose = onClose,
        header = {
            SelectionFromHeader(modifier = Modifier.padding(horizontal = 12.dp)) {
                scope.launch { bottomSheetState.hide() }
                    .invokeOnCompletion {
                        if (!bottomSheetState.isVisible) {
                            onClose()
                        }
                    }
            }
        }
    ) {
        SelectionFromContent(
            modifier = Modifier
                .fillMaxWidth(),
            onGallerySelection = {
                onClose()
                onGallerySelection()
            },
            onCameraSelection = {
                onClose()
                onCameraSelection()
            }
        )
    }
}

@Composable
fun SelectionFromContent(
    modifier: Modifier = Modifier,
    onGallerySelection: () -> Unit,
    onCameraSelection: () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onGallerySelection() }
                .padding(horizontal = 12.dp)
        ) {
            ContactText(
                stringResource(Res.string.select_from_gallery),
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
            ContactIcon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                modifier = Modifier.padding(12.dp).size(24.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onCameraSelection() }
                .padding(horizontal = 12.dp)
        ) {
            ContactText(
                stringResource(Res.string.select_from_camera),
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
            ContactIcon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                modifier = Modifier.padding(12.dp).size(24.dp)
            )
        }
    }
}

@Composable
fun SelectionFromHeader(
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        ContactText(
            stringResource(Res.string.select_image),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        ContactIcon(
            imageVector = Icons.Default.Clear,
            modifier = Modifier
                .clickable {
                    onClose()
                }
                .padding(12.dp)
                .size(24.dp)
        )
    }
}