package com.example.contacts.presentation.detail

import com.example.contacts.presentation.ContactUiModel

sealed class ContactDetailActions {

    data class SaveContact(
        val contact: ContactUiModel
    ) : ContactDetailActions()

    data class UpdateContact(
        val id: Long,
        val contact: ContactUiModel
    ) : ContactDetailActions()

    data class DeleteContact(
        val id: Long
    ) : ContactDetailActions()
}