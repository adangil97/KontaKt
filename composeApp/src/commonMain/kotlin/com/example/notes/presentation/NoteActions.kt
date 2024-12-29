package com.example.notes.presentation

sealed class NoteActions {

    data class DeleteNote(
        val id: Long
    ): NoteActions()

    data class SaveNote(
        val contactId: Long?,
        val note: NoteUiModel
    ): NoteActions()

    data class UpdateNote(
        val id: Long,
        val contactId: Long?,
        val note: NoteUiModel
    ): NoteActions()
}