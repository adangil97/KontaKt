package com.example.contacts.usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactResponse
import com.example.notes.usecases.GetNotesByContactId

class GetContactById(
    private val contactRepository: ContactRepository,
    private val getNotesByContactId: GetNotesByContactId
) {

    suspend operator fun invoke(id: Long): ContactResponse? {
        val notes = getNotesByContactId(id)
        return contactRepository.getById(id)?.copy(notes = notes)
    }
}