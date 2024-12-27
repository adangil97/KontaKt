package com.example.contacts.presentation.list

import cafe.adriel.voyager.core.model.StateScreenModel
import com.example.contacts.usecases.GetContacts
import com.example.contacts.usecases.SearchContacts

class ContactListViewModel(
    private val getContacts: GetContacts,
    private val searchContacts: SearchContacts
) : StateScreenModel<ContactListUiState>(ContactListUiState()) {

}