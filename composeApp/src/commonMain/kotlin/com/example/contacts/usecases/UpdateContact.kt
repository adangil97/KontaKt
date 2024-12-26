package com.example.contacts.usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest

class UpdateContact(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke(
        id: Long,
        contact: ContactRequest
    ) = contactRepository.update(id, contact)
}