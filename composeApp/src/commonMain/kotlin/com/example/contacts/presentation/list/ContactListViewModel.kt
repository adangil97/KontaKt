package com.example.contacts.presentation.list

import com.example.contacts.presentation.toContactUiModel
import com.example.contacts.usecases.GetContacts
import com.example.contacts.usecases.SearchContacts
import com.example.core.ActionsScreenModel

class ContactListViewModel(
    private val getContacts: GetContacts,
    private val searchContacts: SearchContacts
) : ActionsScreenModel<ContactListUiState>(ContactListUiState()) {

    fun initialize() {
        fetchFromActions({
            if (it.searchQuery.isNotBlank()) {
                searchContacts(it.searchQuery)
            } else {
                getContacts()
            }
        }) {
            updateState(
                currentState().copy(
                    isLoading = false,
                    contacts = it.map { contact ->
                        contact.toContactUiModel()
                    }
                )
            )
        }
    }
}