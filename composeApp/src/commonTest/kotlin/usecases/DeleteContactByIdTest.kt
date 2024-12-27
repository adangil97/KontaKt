package usecases

import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactRequest
import com.example.contacts.usecases.DeleteContactById
import data.FakeContactRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DeleteContactByIdTest {
    private lateinit var contactRepository: ContactRepository
    lateinit var deleteContactById: DeleteContactById

    @BeforeTest
    fun setUp() {
        contactRepository = FakeContactRepository()
        runBlocking {
            (1..10).forEach {
                contactRepository.save(
                    ContactRequest(
                        name = "Name $it",
                        lastName = "lastName $it",
                        phoneNumber = "$it",
                        email = "$it@$it"
                    )
                )
            }
        }
        deleteContactById = DeleteContactById(contactRepository)
    }

    @Test
    fun `should delete contact and return deleted contact`() = runBlocking {
        val contactId = 1L
        val result = deleteContactById(contactId)
        assertEquals(contactId, result?.id)
    }

    @Test
    fun `should return null when deleting non-existent contact`() = runBlocking {
        val invalidId = -1L
        val result = deleteContactById(invalidId)
        assertNull(result)
    }
}