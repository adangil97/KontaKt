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
    photo = photo?.toList()
)

fun ContactRequest.toContactDB(id: Long = 0) = ContactDB(
    id = id,
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    email = email,
    photo = photo?.toByteArray()
)