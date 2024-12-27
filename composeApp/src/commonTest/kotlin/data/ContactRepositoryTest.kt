package data

import app.cash.turbine.test
import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.kodein.mock.Mock
import org.kodein.mock.generated.injectMocks
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ContactRepositoryTest : TestsWithMocks() {
    @Mock
    lateinit var contactRepository: ContactRepository

    private val fakeContact = ContactResponse(
        id = 1,
        name = "John Doe",
        phoneNumber = "123456789"
    )

    private val fakeContactTwo = ContactResponse(
        id = 2,
        name = "Jane Doe",
        phoneNumber = "987654321"
    )

    private val fakeContactRequest = ContactRequest(
        name = "John Doe",
        phoneNumber = "123456789"
    )

    override fun setUpMocks() = mocker.injectMocks(this)

    @BeforeTest
    fun setUp() {
        mocker.reset()
        mocker.injectMocks(this)
    }

    @Test
    fun `should retrieve all contacts successfully`() = runBlocking {
        // Given
        val expectedContacts = listOf(fakeContact, fakeContactTwo)
        val fakeContactResponse = flowOf(expectedContacts)
        every { contactRepository.getAll() } returns fakeContactResponse

        // When
        contactRepository.getAll().test {
            // Then
            assertEquals(expectedContacts, awaitItem())
            awaitComplete()
        }
        verify { contactRepository.getAll() }
    }

    @Test
    fun `should retrieve matching contacts when searching`() = runBlocking {
        // Given
        val query = "John"
        val expectedContacts = listOf(fakeContact)
        val fakeContactResponse = flowOf(expectedContacts)
        every { contactRepository.search(query) } returns fakeContactResponse

        // When
        contactRepository.search(query).test {
            // Then
            assertEquals(expectedContacts, awaitItem())
            awaitComplete()
        }
        verify { contactRepository.search(query) }
    }

    @Test
    fun `should save contact and return the new contact`() = runBlocking {
        // Given
        everySuspending { contactRepository.save(fakeContactRequest) } returns fakeContact

        // When
        val result = contactRepository.save(fakeContactRequest)

        // Then
        assertEquals(fakeContact, result)
        verifyWithSuspend { contactRepository.save(fakeContactRequest) }
    }

    @Test
    fun `should update existing contact and return updated contact`() = runBlocking {
        // Given
        val contactId = 1L
        everySuspending {
            contactRepository.update(
                contactId,
                fakeContactRequest
            )
        } returns fakeContact

        // When
        val result = contactRepository.update(contactId, fakeContactRequest)

        // Then
        assertEquals(fakeContact, result)
        verifyWithSuspend { contactRepository.update(contactId, fakeContactRequest) }
    }

    @Test
    fun `should return contact when getting by valid id`() = runBlocking {
        // Given
        val contactId = 1L
        everySuspending { contactRepository.getById(contactId) } returns fakeContact

        // When
        val result = contactRepository.getById(contactId)

        // Then
        assertEquals(fakeContact, result)
        verifyWithSuspend { contactRepository.getById(contactId) }
    }

    @Test
    fun `should return null when getting by invalid id`() = runBlocking {
        // Given
        val invalidId = -1L
        everySuspending { contactRepository.getById(invalidId) } returns null

        // When
        val result = contactRepository.getById(invalidId)

        // Then
        assertNull(result)
        verifyWithSuspend { contactRepository.getById(invalidId) }
    }

    @Test
    fun `should delete contact and return deleted contact`() = runBlocking {
        // Given
        val contactId = 1L
        everySuspending { contactRepository.deleteById(contactId) } returns fakeContact

        // When
        val result = contactRepository.deleteById(contactId)

        // Then
        assertEquals(fakeContact, result)
        verifyWithSuspend { contactRepository.deleteById(contactId) }
    }

    @Test
    fun `should return null when deleting non-existent contact`() = runBlocking {
        // Given
        val invalidId = -1L
        everySuspending { contactRepository.deleteById(invalidId) } returns null

        // When
        val result = contactRepository.deleteById(invalidId)

        // Then
        assertNull(result)
        verifyWithSuspend { contactRepository.deleteById(invalidId) }
    }
}