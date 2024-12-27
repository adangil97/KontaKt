package com.example.contacts.presentation

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