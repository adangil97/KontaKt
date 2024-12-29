package com.example.contacts.presentation

import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse

fun ContactResponse.toContactUiModel() = ContactUiModel(
    id = id,
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    email = email,
    photo = photo?.toByteArray(),
    notes = notes
)

fun ContactUiModel.toContactRequest() = ContactRequest(
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    email = email,
    photo = photo?.toList(),
    notes = notes
)