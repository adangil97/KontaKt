package com.example.notes.framework

import com.example.notes.data.NoteRepository
import com.example.notes.presentation.NoteViewModel
import com.example.notes.usecases.DeleteNoteByContactId
import com.example.notes.usecases.DeleteNoteById
import com.example.notes.usecases.DeleteNotesWithoutContact
import com.example.notes.usecases.GetNotesByContactId
import com.example.notes.usecases.SaveNote
import com.example.notes.usecases.UpdateNote
import org.koin.dsl.module

val noteModule = module {
    single<NoteRepository> { DBNoteRepository(get()) }
    single { DeleteNoteByContactId(get()) }
    single { DeleteNoteById(get()) }
    single { DeleteNotesWithoutContact(get()) }
    single { GetNotesByContactId(get()) }
    single { SaveNote(get()) }
    single { UpdateNote(get()) }
    factory {
        NoteViewModel(get(), get(), get(), get())
    }
}