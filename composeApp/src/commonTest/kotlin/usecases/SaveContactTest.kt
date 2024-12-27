package usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.usecases.SaveContact
import data.FakeContactRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SaveContactTest {
    private lateinit var contactRepository: ContactRepository
    lateinit var saveContact: SaveContact

    private val fakeContactRequest = ContactRequest(
        name = "John Doe",
        phoneNumber = "123456789"
    )

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
        saveContact = SaveContact(contactRepository)
    }

    @Test
    fun `should save contact and return the new contact`() = runBlocking {
        val expectedId = 11L
        val result = saveContact(fakeContactRequest)
        assertEquals(expectedId, result.id)
    }
}