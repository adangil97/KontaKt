package usecases

import app.cash.turbine.test
import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactResponse
import com.example.contacts.usecases.SearchContacts
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.kodein.mock.Mock
import org.kodein.mock.generated.injectMocks
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchContactsTest : TestsWithMocks() {
    @Mock
    lateinit var contactRepository: ContactRepository
    lateinit var searchContacts: SearchContacts

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
        searchContacts = SearchContacts(contactRepository)
    }

    @Test
    fun `should retrieve matching contacts when searching`() = runBlocking {
        // Given
        val query = "John"
        val expectedContacts = listOf(fakeContact)
        val fakeContactResponse = flowOf(expectedContacts)
        every { searchContacts(query) } returns fakeContactResponse

        // When
        searchContacts(query).test {
            // Then
            assertEquals(expectedContacts, awaitItem())
            awaitComplete()
        }
        verify { searchContacts(query) }
    }
}