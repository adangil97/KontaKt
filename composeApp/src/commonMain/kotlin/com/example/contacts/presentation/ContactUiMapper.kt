package com.example.contacts.presentation

import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse
import com.example.notes.presentation.toNoteUiModel

fun ContactResponse.toContactUiModel() = ContactUiModel(
    id = id,
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    email = email,
    photo = photo?.toByteArray(),
    notes = notes?.map { it.toNoteUiModel() }
)

fun ContactUiModel.toContactRequest() = ContactRequest(
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    email = email,
    photo = photo?.toList()
)