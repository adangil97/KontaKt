package com.example.notes.framework

import com.example.notes.domain.Note
import com.example.contacts.Note as NoteDB

fun NoteDB.toNote() = Note(
    id = id,
    contactId = contactId,
    note = note
)

fun Note.toNoteDB(id: Long = 0) = NoteDB(
    id = id,
    contactId = contactId,
    note = note
)