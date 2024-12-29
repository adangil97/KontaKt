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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContactDetailViewModel(
    private val saveContact: SaveContact,
    private val updateContact: UpdateContact,
    private val getContactById: GetContactById,
    private val deleteContactById: DeleteContactById
) : StateEffectScreenModel<ContactDetailUiState, ContactDetailUiEffects>(ContactDetailUiState()) {

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
}