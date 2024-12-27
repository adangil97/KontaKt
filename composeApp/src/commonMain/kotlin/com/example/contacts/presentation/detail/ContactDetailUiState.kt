package com.example.contacts.presentation.detail

data class ContactDetailUiState(
    val isLoading: Boolean = false
)

sealed class ContactDetailUiEffects {

    data object Success : ContactDetailUiEffects()
}
