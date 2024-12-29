package com.example.notes.usecases

import com.example.notes.data.NoteRepository
import com.example.notes.domain.Note

class UpdateNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(
        id: Long,
        note: Note
    ) = noteRepository.update(id, note)
}