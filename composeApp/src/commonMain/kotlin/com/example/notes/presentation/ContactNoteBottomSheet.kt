package com.example.notes.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core.ds.ContactBottomSheet
import com.example.core.ds.ContactText
import com.example.core.ds.ContactTextField
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.contact_detail_note_label
import kontakt.composeapp.generated.resources.contact_detail_note_required
import kontakt.composeapp.generated.resources.contact_detail_notes
import kontakt.composeapp.generated.resources.msg_ok
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactNoteBottomSheet(
    noteUiModel: NoteUiModel,
    onNote: (NoteUiModel) -> Unit,
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var note: String by remember { mutableStateOf(noteUiModel.note) }
    var noteError: String? by remember { mutableStateOf(null) }
    val noteRequiredMsg = stringResource(Res.string.contact_detail_note_required)
    ContactBottomSheet(
        sheetState = bottomSheetState,
        modifier = modifier,
        onClose = onClose,
        header = {
            NoteHeader(
                title = stringResource(Res.string.contact_detail_notes),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                if (note.isNotBlank()) {
                    scope.launch { bottomSheetState.hide() }
                        .invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                onNote(
                                    noteUiModel.copy(
                                        note = note
                                    )
                                )
                                onClose()
                            }
                        }
                } else {
                    noteError = noteRequiredMsg
                }
            }
        }
    ) {
        NoteContent(
            detail = note,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            msgError = noteError,
            onDetailChange = {
                if (it.isNotBlank()) {
                    noteError = null
                }
                note = it
            }
        )
    }
}

@Composable
fun NoteHeader(
    title: String,
    modifier: Modifier = Modifier,
    onOk: () -> Unit
) {
    Row(modifier = modifier) {
        val msgOk = stringResource(Res.string.msg_ok)
        ContactText(
            value = title,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        ContactText(
            value = msgOk,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(onClick = onOk)
        )
    }
}

@Composable
fun NoteContent(
    detail: String,
    msgError: String?,
    modifier: Modifier = Modifier,
    onDetailChange: (String) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val saveRequestTitle = stringResource(Res.string.contact_detail_note_label)
        ContactTextField(
            value = detail,
            msgError = msgError,
            onValueChange = onDetailChange,
            labelValue = saveRequestTitle,
            modifier = Modifier.fillMaxWidth(.7f)
        )
    }
}