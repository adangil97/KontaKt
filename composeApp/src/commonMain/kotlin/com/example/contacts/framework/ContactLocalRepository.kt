package com.example.contacts.framework

import app.cash.sqldelight.coroutines.asFlow
import com.example.contacts.ContactDatabase
import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactLocalRepository(
    private val contactDatabase: ContactDatabase
) : ContactRepository {

    override fun getAll(): Flow<List<ContactResponse>> {
        return contactDatabase.contactDatabaseQueries.getAllContacts().asFlow().map {
            it.executeAsList().map { contact ->
                contact.toContactResponse()
            }
        }
    }

    override fun search(query: String): Flow<List<ContactResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun save(contact: ContactRequest): ContactResponse {
        TODO("Not yet implemented")
    }

    override suspend fun update(
        id: Long,
        contact: ContactRequest
    ): ContactResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): ContactResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteById(id: Long): ContactResponse? {
        TODO("Not yet implemented")
    }
}