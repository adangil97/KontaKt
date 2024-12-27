package com.example.contacts.framework

import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse
import com.example.contacts.Contact as ContactDB

fun ContactDB.toContactResponse() = ContactResponse(
    id = id,
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    email = email,
    photo = photo?.toList(),
    notes = notes?.split(",")
)

fun ContactRequest.toContactDB() = ContactDB(
    id = 0,
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    email = email,
    photo = photo?.toByteArray(),
    notes = notes?.joinToString(",")
)