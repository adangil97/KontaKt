package com.example.contacts.domain

data class ContactRequest(
    val name: String,
    val lastName: String?,
    val phoneNumber: String?,
    val email: String?,
    val photo: List<Byte>?,
    val notes: List<String>?
)

data class ContactResponse(
    val id: Long,
    val name: String,
    val lastName: String?,
    val phoneNumber: String?,
    val email: String?,
    val photo: List<Byte>?,
    val notes: List<String>?
)