package com.example.contacts.presentation.detail

import com.example.contacts.presentation.ContactUiModel

data class ContactDetailUiState(
    val isLoading: Boolean = true,
    val alreadyInitialized: Boolean = false,
    val contact: ContactUiModel = ContactUiModel()
)

sealed class ContactDetailUiEffects {

    data object GoBack : ContactDetailUiEffects()

    data object ShowUpdateSuccess : ContactDetailUiEffects()
}
