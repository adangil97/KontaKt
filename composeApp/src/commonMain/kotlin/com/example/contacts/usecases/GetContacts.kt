package com.example.contacts.usecases

import com.example.contacts.data.ContactRepository

class GetContacts(
    private val contactRepository: ContactRepository
) {

    operator fun invoke() = contactRepository.getAll()
}