package com.example.notes.presentation

import com.example.notes.domain.Note

fun NoteUiModel.toNote(contactId: Long?) = Note(
    id = id,
    contactId = contactId,
    note = note
)

fun Note.toNoteUiModel() = NoteUiModel(
    id = id,
    note = note
)