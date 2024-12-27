package com.example.contacts.presentation.detail

import cafe.adriel.voyager.core.model.screenModelScope
import com.example.contacts.presentation.ContactUiRequestModel
import com.example.contacts.presentation.toContactRequest
import com.example.contacts.presentation.toContactUiModel
import com.example.contacts.usecases.DeleteContactById
import com.example.contacts.usecases.GetContactById
import com.example.contacts.usecases.SaveContact
import com.example.contacts.usecases.UpdateContact
import com.example.core.StateEffectScreenModel
import kotlinx.coroutines.launch

class ContactDetailViewModel(
    private val saveContact: SaveContact,
    private val updateContact: UpdateContact,
    private val getContactById: GetContactById,
    private val deleteContactById: DeleteContactById
) : StateEffectScreenModel<ContactDetailUiState, ContactDetailUiEffects>(ContactDetailUiState()) {

    fun fetchContactIfRequired(id: Long?) {
        screenModelScope.launch {
            if (id != null) {
                val contact = getContactById(id)?.toContactUiModel()
                updateState(
                    currentState().copy(
                        contact = contact
                    )
                )
            }
        }
    }

    fun dispatchContact(
        id: Long?,
        contact: ContactUiRequestModel
    ) {
        screenModelScope.launch {
            updateState(currentState().copy(isLoading = true))
            val contactRequest = contact.toContactRequest()
            if (id != null) {
                updateContact(id, contactRequest)
            } else {
                saveContact(contactRequest)
            }
            updateState(currentState().copy(isLoading = false))
            launchEffect(ContactDetailUiEffects.Success)
        }
    }

    fun deleteContact(id: Long) {
        screenModelScope.launch {
            updateState(currentState().copy(isLoading = true))
            deleteContactById(id)
            updateState(currentState().copy(isLoading = false))
            launchEffect(ContactDetailUiEffects.Success)
        }
    }
}