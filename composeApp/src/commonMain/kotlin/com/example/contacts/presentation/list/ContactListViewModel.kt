package com.example.contacts.presentation.list

import cafe.adriel.voyager.core.model.screenModelScope
import com.example.contacts.presentation.toContactUiModel
import com.example.contacts.usecases.SearchContacts
import com.example.core.ActionsScreen
import com.example.core.ActionsScreenModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val getContacts: com.example.contacts.usecases.GetContacts,
    private val searchContacts: SearchContacts
) : ActionsScreenModel<ContactListUiState>(ContactListUiState()) {

    fun initialize() {
        screenModelScope.launch {
            fetchFromActions({
                updateState(currentState().copy(query = it.searchQuery))
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
            delay(1000)
            sendAction(ActionsScreen(currentState().query))
        }
    }
}