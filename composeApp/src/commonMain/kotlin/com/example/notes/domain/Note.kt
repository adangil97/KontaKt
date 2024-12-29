package com.example.notes.domain

data class Note(
    val id: Long,
    val contactId: Long?,
    val note: String
)
