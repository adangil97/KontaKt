package usecases

import app.cash.turbine.test
import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.usecases.GetContacts
import data.FakeContactRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetContactsTest {
    private lateinit var contactRepository: ContactRepository
    lateinit var getContacts: GetContacts

    @BeforeTest
    fun setUp() {
        contactRepository = FakeContactRepository()
        val contactsToInsert = mutableListOf<ContactRequest>()
        (1..10).forEach {
            contactsToInsert.add(
                ContactRequest(
                    name = "Name $it",
                    lastName = "lastName $it",
                    phoneNumber = "$it",
                    email = "$it@$it"
                )
            )
        }
        contactsToInsert.shuffle()
        runBlocking {
            contactsToInsert.forEach { contactRepository.save(it) }
        }
        getContacts = GetContacts(contactRepository)
    }

    @Test
    fun `should retrieve all contacts successfully`() = runBlocking {
        val expectedContactsSize = 10
        getContacts().test {
            // Then
            assertEquals(expectedContactsSize, awaitItem().size)
            awaitComplete()
        }
    }
}