package com.example.notes.data

import com.example.notes.domain.Note

interface NoteRepository {

    suspend fun save(note: Note)
    
    suspend fun update(id: Long, note: Note)

    fun getByContactId(contactId: Long): List<Note>

    suspend fun deleteById(id: Long)
}