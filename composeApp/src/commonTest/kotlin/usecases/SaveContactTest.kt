package usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.domain.ContactResponse
import com.example.contacts.usecases.SaveContact
import kotlinx.coroutines.runBlocking
import org.kodein.mock.Mock
import org.kodein.mock.generated.injectMocks
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SaveContactTest : TestsWithMocks() {
    @Mock
    lateinit var contactRepository: ContactRepository
    lateinit var saveContact: SaveContact

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
        saveContact = SaveContact(contactRepository)
    }

    @Test
    fun `should save contact and return the new contact`() = runBlocking {
        // Given
        everySuspending { saveContact(fakeContactRequest) } returns fakeContact

        // When
        val result = saveContact(fakeContactRequest)

        // Then
        assertEquals(fakeContact, result)
        verifyWithSuspend { saveContact(fakeContactRequest) }
    }
}