package com.example.core.ds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onClose,
        sheetState = sheetState,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(bottom = 48.dp)) {
            header?.let {
                it()
                ContactDivider(modifier = Modifier)
            }
            content()
        }
    }
}