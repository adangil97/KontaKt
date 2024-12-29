package com.example.notes.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.notes.usecases.DeleteNoteById
import com.example.notes.usecases.DeleteNotesWithoutContact
import com.example.notes.usecases.SaveNote
import com.example.notes.usecases.UpdateNote
import kotlinx.coroutines.launch

class NoteViewModel(
    private val deleteNoteById: DeleteNoteById,
    private val deleteNotesWithoutContact: DeleteNotesWithoutContact,
    private val saveNote: SaveNote,
    private val updateNote: UpdateNote
) : ScreenModel {

    fun initialize() {
        screenModelScope.launch {
            deleteNotesWithoutContact()
        }
    }

    fun dispatchNote(
        id: Long? = null,
        contactId: Long? = null,
        note: NoteUiModel
    ) {
        screenModelScope.launch {
            val noteRequest = note.toNote(contactId)
            if (id != null) {
                updateNote(id, noteRequest)
            } else {
                saveNote(noteRequest)
            }
        }
    }

    fun deleteNote(id: Long) {
        screenModelScope.launch {
            deleteNoteById(id)
        }
    }
}