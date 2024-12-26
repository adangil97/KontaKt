package com.example.contacts.usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest

class SaveContact(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke(contact: ContactRequest) = contactRepository.save(contact)
}