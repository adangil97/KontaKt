package com.example

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.contacts.presentation.ContactUiModel
import com.example.contacts.presentation.detail.ContactDetailContent

@Preview(showSystemUi = true)
@Composable
fun ContentPreview(modifier: Modifier = Modifier) {
    ContactDetailContent(
        contactUiModel = ContactUiModel(),
        isLoading = false,
        updateState = {},
        onRequestPhoto = {

        },
        onNoteActions = {}
    ) {}
}