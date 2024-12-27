package com.example.contacts.framework

import com.example.contacts.Contact
import com.example.contacts.domain.ContactResponse

fun Contact.toContactResponse() = ContactResponse(
    id = id,
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    email = email,
    photo = photo?.toList(),
    notes = notes?.split(",")
)