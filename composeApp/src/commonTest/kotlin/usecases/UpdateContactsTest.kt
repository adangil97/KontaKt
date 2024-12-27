package usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse
import com.example.contacts.usecases.UpdateContact
import kotlinx.coroutines.runBlocking
import org.kodein.mock.Mock
import org.kodein.mock.generated.injectMocks
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UpdateContactsTest : TestsWithMocks() {
    @Mock
    lateinit var contactRepository: ContactRepository
    lateinit var updateContact: UpdateContact

    private val fakeContact = ContactResponse(
        id = 1,
        name = "John Doe",
        phoneNumber = "123456789"
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
        updateContact = UpdateContact(contactRepository)
    }

    @Test
    fun `should update existing contact and return updated contact`() = runBlocking {
        // Given
        val contactId = 1L
        everySuspending { updateContact(contactId, fakeContactRequest) } returns fakeContact

        // When
        val result = updateContact(contactId, fakeContactRequest)

        // Then
        assertEquals(fakeContact, result)
        verifyWithSuspend { updateContact(contactId, fakeContactRequest) }
    }
}