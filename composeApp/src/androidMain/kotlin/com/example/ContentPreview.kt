package com.example

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.contacts.presentation.ContactUiModel
import com.example.contacts.presentation.detail.ContactDetailContent
import com.example.notes.presentation.NoteUiModel

@Preview(showSystemUi = true)
@Composable
fun ContentPreview(modifier: Modifier = Modifier) {
    ContactDetailContent(
        contactUiModel = ContactUiModel(
            notes = listOf(
                NoteUiModel(
                    id = 1,
                    note = "Nota 1"
                ),
                NoteUiModel(
                    id = 2,
                    note = "Nota 2"
                )
            )
        ),
        isLoading = false,
        updateState = {},
        onRequestPhoto = {

        },
        onUpdateContact = { _, _ -> },
        onDeleteContact = {}
    ) {}
}