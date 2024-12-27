package com.example.contacts.presentation.detail

import com.example.contacts.presentation.ContactUiModel

data class ContactDetailUiState(
    val isLoading: Boolean = false,
    val contact: ContactUiModel? = null
)

sealed class ContactDetailUiEffects {

    data object Success : ContactDetailUiEffects()
}
