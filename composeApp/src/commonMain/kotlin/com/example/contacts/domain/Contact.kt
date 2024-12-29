package com.example.contacts.domain

import com.example.notes.domain.Note

data class ContactRequest(
    val name: String,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val photo: List<Byte>? = null
)

data class ContactResponse(
    val id: Long,
    val name: String,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val photo: List<Byte>? = null,
    val notes: List<Note>? = null
)