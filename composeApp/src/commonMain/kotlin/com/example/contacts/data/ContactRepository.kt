package com.example.contacts.data

import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun getAll(): Flow<List<ContactResponse>>

    fun search(query: String): Flow<List<ContactResponse>>

    suspend fun save(contact: ContactRequest): ContactResponse

    suspend fun update(
        id: Long,
        contact: ContactRequest
    ): ContactResponse

    suspend fun getById(id: Long): ContactResponse?

    suspend fun deleteById(id: Long): ContactResponse?
}