package com.example.contacts.domain

data class ContactRequest(
    val name: String,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val photo: List<Byte>? = null,
    val notes: List<String>? = null
)

data class ContactResponse(
    val id: Long,
    val name: String,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val photo: List<Byte>? = null,
    val notes: List<String>? = null
)