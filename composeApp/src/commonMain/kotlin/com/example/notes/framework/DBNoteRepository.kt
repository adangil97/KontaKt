package com.example.notes.framework

import com.example.contacts.NotesDatabaseQueries
import com.example.notes.data.NoteRepository
import com.example.notes.domain.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class DBNoteRepository(
    private val noteDao: NotesDatabaseQueries
) : NoteRepository {

    override suspend fun save(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.saveNote(note.toNoteDB())
        }
    }

    override suspend fun update(id: Long, note: Note) {
        noteDao.findNoteById(id).executeAsOneOrNull()?.let {
            noteDao.updateContact(note.toNoteDB(id = it.id))
        }
    }

    override suspend fun getByContactId(contactId: Long): List<Note> = withContext(Dispatchers.IO) {
        noteDao.findNotesByContactId(contactId).executeAsList().map { noteDB ->
            noteDB.toNote()
        }
    }

    override suspend fun deleteById(id: Long) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNoteById(id)
        }
    }

    override suspend fun deleteByContactId(id: Long) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNoteByContactId(id)
        }
    }

    override suspend fun deleteNotesWithContactIdNull() {
        withContext(Dispatchers.IO) {
            noteDao.deleteNotesWithContactIdNull()
        }
    }
}