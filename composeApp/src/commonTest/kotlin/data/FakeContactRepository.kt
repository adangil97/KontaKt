package data

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeContactRepository : ContactRepository {
    private val contactList: MutableSet<ContactResponse> = mutableSetOf()

    override fun getAll(): Flow<List<ContactResponse>> {
        return flowOf(contactList.sortedBy { it.id })
    }

    override fun search(query: String): Flow<List<ContactResponse>> {
        return flowOf(
            contactList.filter {
                it.name.contains(query) ||
                        it.lastName.orEmpty().contains(query) ||
                        it.notes.orEmpty().contains(query)
            }.sortedBy { it.id }
        )
    }

    override suspend fun save(contact: ContactRequest): ContactResponse {
        val newContact = ContactResponse(
            id = (contactList.size + 1).toLong(),
            name = contact.name,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber,
            email = contact.email,
            photo = contact.photo,
            notes = contact.notes
        )
        contactList.add(newContact)
        return newContact
    }

    override suspend fun update(
        id: Long,
        contact: ContactRequest
    ): ContactResponse? {
        return getById(id)?.let {
            val newContact = ContactResponse(
                id = it.id,
                name = contact.name,
                lastName = contact.lastName,
                phoneNumber = contact.phoneNumber,
                email = contact.email,
                photo = contact.photo,
                notes = contact.notes
            )
            contactList.add(newContact)
            newContact
        }
    }

    override suspend fun getById(id: Long): ContactResponse? {
        return contactList.find {
            it.id == id
        }
    }

    override suspend fun deleteById(id: Long): ContactResponse? {
        return getById(id)?.let {
            contactList.remove(it)
            it
        }
    }
}