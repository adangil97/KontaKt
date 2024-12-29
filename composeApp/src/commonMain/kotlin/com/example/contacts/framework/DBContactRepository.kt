package com.example.contacts.framework

import app.cash.sqldelight.coroutines.asFlow
import com.example.contacts.ContactDatabaseQueries
import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DBContactRepository(
    private val contactDao: ContactDatabaseQueries
) : ContactRepository {

    override fun getAll(): Flow<List<ContactResponse>> {
        return contactDao.getAllContacts().asFlow().map { query ->
            query.executeAsList().map { contact ->
                contact.toContactResponse()
            }
        }
    }

    override fun search(searchQuery: String): Flow<List<ContactResponse>> {
        return contactDao.searchContacts(searchQuery).asFlow().map { query ->
            query.executeAsList().map { contact ->
                contact.toContactResponse()
            }
        }
    }

    override suspend fun save(contact: ContactRequest) {
        withContext(Dispatchers.IO) {
            contactDao.saveContact(contact.toContactDB())
        }
    }

    override suspend fun update(
        id: Long,
        contact: ContactRequest
    ) {
        withContext(Dispatchers.IO) {
            getById(id)?.let {
                val newContact = contact.toContactDB(it.id)
                contactDao.updateContact(newContact)
            }
        }
    }

    override suspend fun getById(id: Long): ContactResponse? {
        return withContext(Dispatchers.IO) {
            contactDao.getContactById(id).executeAsOneOrNull()?.toContactResponse()
        }
    }

    override suspend fun deleteById(id: Long): ContactResponse? {
        return withContext(Dispatchers.IO) {
            getById(id)?.let {
                contactDao.deleteContactById(id)
                it
            }
        }
    }
}