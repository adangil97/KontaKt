package com.example.notes.framework

import com.example.notes.data.NoteRepository
import com.example.notes.usecases.DeleteNoteById
import com.example.notes.usecases.GetNotesByContactId
import com.example.notes.usecases.SaveNote
import com.example.notes.usecases.UpdateNote
import org.koin.dsl.module

val noteModule = module {
    single<NoteRepository> { DBNoteRepository(get()) }
    single { DeleteNoteById(get()) }
    single { GetNotesByContactId(get()) }
    single { SaveNote(get()) }
    single { UpdateNote(get()) }
}