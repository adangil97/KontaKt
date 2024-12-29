package com.example.contacts.usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.notes.usecases.GetNotesWithoutContact
import com.example.notes.usecases.UpdateNote

class SaveContact(
    private val contactRepository: ContactRepository,
    private val getNotesWithoutContact: GetNotesWithoutContact,
    private val updateNote: UpdateNote
) {

    suspend operator fun invoke(contact: ContactRequest) {
        contactRepository.save(contact)
        val lastContactId = contactRepository.getLastId()
        getNotesWithoutContact().forEach { note ->
            updateNote(
                id = note.id,
                note = note.copy(
                    contactId = lastContactId
                )
            )
        }
    }
}