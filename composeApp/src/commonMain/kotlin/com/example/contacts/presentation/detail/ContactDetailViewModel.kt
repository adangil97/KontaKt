package com.example.contacts.presentation.detail

import com.example.contacts.usecases.DeleteContactById
import com.example.contacts.usecases.GetContactById
import com.example.contacts.usecases.SaveContact
import com.example.contacts.usecases.UpdateContact
import com.example.core.StateEffectScreenModel

class ContactDetailViewModel(
    private val saveContact: SaveContact,
    private val updateContact: UpdateContact,
    private val getContactById: GetContactById,
    private val deleteContactById: DeleteContactById
): StateEffectScreenModel<ContactDetailUiState, ContactDetailUiEffects>(ContactDetailUiState()) {


}