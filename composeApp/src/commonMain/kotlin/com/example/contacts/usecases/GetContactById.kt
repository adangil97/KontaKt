package com.example.contacts.usecases

import com.example.contacts.data.ContactRepository

class GetContactById(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke(id: Long) = contactRepository.getById(id)
}