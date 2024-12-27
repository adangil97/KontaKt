package com.example.contacts.presentation.list

import com.example.contacts.presentation.ContactUiModel

data class ContactListUiState(
    val isLoading: Boolean = true,
    val contacts: List<ContactUiModel> = listOf()
)
