package usecases

import app.cash.turbine.test
import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactResponse
import com.example.contacts.usecases.GetContacts
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.kodein.mock.Mock
import org.kodein.mock.generated.injectMocks
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetContactsTest : TestsWithMocks() {
    @Mock
    lateinit var contactRepository: ContactRepository
    lateinit var getContacts: GetContacts

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

    override fun setUpMocks() = mocker.injectMocks(this)

    @BeforeTest
    fun setUp() {
        mocker.reset()
        mocker.injectMocks(this)
        getContacts = GetContacts(contactRepository)
    }

    @Test
    fun `should retrieve all contacts successfully`() = runBlocking {
        // Given
        val expectedContacts = listOf(fakeContact, fakeContactTwo)
        val fakeContactResponse = flowOf(expectedContacts)
        every { getContacts() } returns fakeContactResponse

        // When
        getContacts().test {
            // Then
            assertEquals(expectedContacts, awaitItem())
            awaitComplete()
        }
        verify { getContacts() }
    }
}