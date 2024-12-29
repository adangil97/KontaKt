package com.example.notes.usecases

import com.example.notes.data.NoteRepository

class GetNotesWithoutContact(private val noteRepository: NoteRepository) {
    
    suspend operator fun invoke() = noteRepository.getWithoutContactId()
}