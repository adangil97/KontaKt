package com.example.notes.usecases

import com.example.notes.data.NoteRepository

class DeleteNotesWithoutContact(private val noteRepository: NoteRepository) {

    suspend operator fun invoke() = noteRepository.deleteNotesWithContactIdNull()
}