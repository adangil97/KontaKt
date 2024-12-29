package com.example.notes.usecases

import com.example.notes.data.NoteRepository

class DeleteNoteByContactId(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(contactId: Long) = noteRepository.deleteByContactId(contactId)
}