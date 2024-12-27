package usecases

import app.cash.turbine.test
import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.usecases.SearchContacts
import data.FakeContactRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchContactsTest {
    private lateinit var contactRepository: ContactRepository
    lateinit var searchContacts: SearchContacts

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
        searchContacts = SearchContacts(contactRepository)
    }

    @Test
    fun `should retrieve matching contacts when searching with not empty query`() = runBlocking {
        val query = "1"
        val expectedContactSie = 2

        // When
        searchContacts(query).test {
            // Then
            assertEquals(expectedContactSie, awaitItem().size)
            awaitComplete()
        }
    }

    @Test
    fun `should retrieve all contacts when searching with empty query`() = runBlocking {
        val query = ""
        val expectedContactSie = 10

        // When
        searchContacts(query).test {
            // Then
            assertEquals(expectedContactSie, awaitItem().size)
            awaitComplete()
        }
    }
}