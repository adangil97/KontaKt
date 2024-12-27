package com.example.contacts.framework

import com.example.contacts.ContactDatabaseQueries
import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse
import kotlinx.coroutines.flow.Flow

class ContactLocalRepository(private val contactDao: ContactDatabaseQueries) : ContactRepository {

    override fun getAll(): Flow<List<ContactResponse>> {
        TODO("Not yet implemented")
    }

    override fun search(query: String): Flow<List<ContactResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun save(contact: ContactRequest): ContactResponse {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: Long, contact: ContactRequest): ContactResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): ContactResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteById(id: Long): ContactResponse? {
        TODO("Not yet implemented")
    }
}