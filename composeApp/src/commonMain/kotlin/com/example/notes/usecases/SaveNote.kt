package com.example.notes.usecases

import com.example.notes.data.NoteRepository
import com.example.notes.domain.Note

class SaveNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.save(note)
}