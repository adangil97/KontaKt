package usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactResponse
import com.example.contacts.usecases.DeleteContactById
import kotlinx.coroutines.runBlocking
import org.kodein.mock.Mock
import org.kodein.mock.generated.injectMocks
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DeleteContactByIdTest : TestsWithMocks() {
    @Mock
    lateinit var contactRepository: ContactRepository
    lateinit var deleteContactById: DeleteContactById

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
        deleteContactById = DeleteContactById(contactRepository)
    }

    @Test
    fun `should delete contact and return deleted contact`() = runBlocking {
        // Given
        val contactId = 1L
        everySuspending { deleteContactById(contactId) } returns fakeContact

        // When
        val result = deleteContactById(contactId)

        // Then
        assertEquals(fakeContact, result)
        verifyWithSuspend { deleteContactById(contactId) }
    }

    @Test
    fun `should return null when deleting non-existent contact`() = runBlocking {
        // Given
        val invalidId = -1L
        everySuspending { deleteContactById(invalidId) } returns null

        // When
        val result = deleteContactById(invalidId)

        // Then
        assertNull(result)
        verifyWithSuspend { deleteContactById(invalidId) }
    }
}