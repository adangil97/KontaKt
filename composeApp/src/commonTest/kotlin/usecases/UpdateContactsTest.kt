package usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.usecases.UpdateContact
import data.FakeContactRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class UpdateContactsTest {
    private lateinit var contactRepository: ContactRepository
    lateinit var updateContact: UpdateContact

    @BeforeTest
    fun setUp() {
        contactRepository = FakeContactRepository()
        runBlocking {
            (1..10).forEach {
                contactRepository.save(
                    ContactRequest(
                        name = "Name $it",
                        lastName = "lastName $it",
                        phoneNumber = "$it",
                        email = "$it@$it"
                    )
                )
            }
        }
        updateContact = UpdateContact(contactRepository)
    }

    @Test
    fun `should update existing contact and return updated contact`() = runBlocking {
        val contact = ContactRequest(name = "Fake name")
        val contactId = 1L
        val result = updateContact(contactId, contact)
        assertEquals(contactId, result?.id)
    }

    @Test
    fun `should return null when contact id not exists`() = runBlocking {
        val contact = ContactRequest(name = "Fake name")
        val contactId = -1L
        val result = updateContact(contactId, contact)
        assertNull(result)
    }
}