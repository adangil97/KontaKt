package com.example.contacts.presentation.detail

import cafe.adriel.voyager.core.model.screenModelScope
import com.example.contacts.presentation.ContactUiModel
import com.example.contacts.presentation.toContactRequest
import com.example.contacts.presentation.toContactUiModel
import com.example.contacts.usecases.DeleteContactById
import com.example.contacts.usecases.GetContactById
import com.example.contacts.usecases.SaveContact
import com.example.contacts.usecases.UpdateContact
import com.example.core.StateEffectScreenModel
import com.example.notes.presentation.toNoteUiModel
import com.example.notes.usecases.GetNotesByContactId
import com.example.notes.usecases.GetNotesWithoutContact
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContactDetailViewModel(
    private val saveContact: SaveContact,
    private val updateContact: UpdateContact,
    private val getContactById: GetContactById,
    private val deleteContactById: DeleteContactById,
    private val getNotesByContactId: GetNotesByContactId,
    private val getNotesWithoutContact: GetNotesWithoutContact
) : StateEffectScreenModel<ContactDetailUiState, ContactDetailUiEffects>(ContactDetailUiState()) {

    fun updatePhoto(photo: ByteArray?) {
        val currentContact = currentState().contact
        updateState(
            currentState().copy(
                contact = currentContact.copy(
                    photo = photo
                )
            )
        )
    }

    fun getContactIfRequired(id: Long?) {
        screenModelScope.launch {
            if (id != null) {
                updateState(currentState().copy(isLoading = true))
                delay(1000)
                getContactById(id)?.toContactUiModel()?.let { contact ->
                    updateState(
                        currentState().copy(
                            isLoading = false,
                            contact = contact
                        )
                    )
                }
            }
            updateState(
                currentState().copy(
                    isLoading = false,
                    alreadyInitialized = true
                )
            )
        }
    }

    fun dispatchContact(
        id: Long? = null,
        contact: ContactUiModel
    ) {
        screenModelScope.launch {
            updateState(currentState().copy(isLoading = true))
            val contactRequest = contact.toContactRequest()
            delay(1000)
            if (id != null) {
                updateContact(id, contactRequest)
            } else {
                saveContact(contactRequest)
            }
            updateState(currentState().copy(isLoading = false))
            launchEffect(ContactDetailUiEffects.GoBack)
        }
    }

    fun deleteContact(id: Long) {
        screenModelScope.launch {
            updateState(currentState().copy(isLoading = true))
            delay(1000)
            deleteContactById(id)
            updateState(currentState().copy(isLoading = false))
            launchEffect(ContactDetailUiEffects.GoBack)
        }
    }

    fun updateNotes(contactId: Long?) {
        screenModelScope.launch {
            delay(100)
            val currentState = currentState()
            val notes = if (contactId != null) {
                getNotesByContactId(contactId)
            } else {
                getNotesWithoutContact()
            }
            updateState(
                currentState.copy(
                    contact = currentState.contact.copy(
                        notes = notes.map { note ->
                            note.toNoteUiModel()
                        }
                    )
                )
            )
        }
    }
}