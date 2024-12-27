package com.example.contacts.usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactResponse
import kotlinx.coroutines.flow.Flow

class SearchContacts(
    private val contactRepository: ContactRepository
) {

    operator fun invoke(query: String): Flow<List<ContactResponse>> = if (query.isNotBlank()) {
        contactRepository.search(query)
    } else {
        contactRepository.getAll()
    }
}