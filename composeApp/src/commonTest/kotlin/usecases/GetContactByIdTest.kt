package usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactResponse
import com.example.contacts.usecases.GetContactById
import kotlinx.coroutines.runBlocking
import org.kodein.mock.Mock
import org.kodein.mock.generated.injectMocks
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetContactByIdTest: TestsWithMocks() {
    @Mock
    lateinit var contactRepository: ContactRepository
    lateinit var getContactById: GetContactById

    private val fakeContact = ContactResponse(
        id = 1,
        name = "John Doe",
        phoneNumber = "123456789"
    )

    override fun setUpMocks() = mocker.injectMocks(this)

    @BeforeTest
    fun setUp() {
        mocker.reset()
        mocker.injectMocks(this)
        getContactById = GetContactById(contactRepository)
    }

    @Test
    fun `should delete contact and return deleted contact`() = runBlocking {
        // Given
        val contactId = 1L
        everySuspending { getContactById(contactId) } returns fakeContact

        // When
        val result = getContactById(contactId)

        // Then
        assertEquals(fakeContact, result)
        verifyWithSuspend { getContactById(contactId) }
    }

    @Test
    fun `should return null when deleting non-existent contact`() = runBlocking {
        // Given
        val invalidId = -1L
        everySuspending { getContactById(invalidId) } returns null

        // When
        val result = getContactById(invalidId)

        // Then
        assertNull(result)
        verifyWithSuspend { getContactById(invalidId) }
    }
}