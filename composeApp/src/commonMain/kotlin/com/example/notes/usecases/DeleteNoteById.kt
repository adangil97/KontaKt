package com.example.notes.usecases

import com.example.notes.data.NoteRepository

class DeleteNoteById(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(id: Long) = noteRepository.deleteById(id)
}