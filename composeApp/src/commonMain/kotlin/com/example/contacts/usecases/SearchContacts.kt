package com.example.contacts.usecases

import com.example.contacts.data.ContactRepository

class SearchContacts(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke(query: String) = contactRepository.search(query)
}