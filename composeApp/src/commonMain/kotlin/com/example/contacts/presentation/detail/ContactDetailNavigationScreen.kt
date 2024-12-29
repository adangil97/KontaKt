package com.example.contacts.presentation.detail

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.core.ContactUniqueScreen

class ContactDetailNavigationScreen(private val contactId: Long? = null) : ContactUniqueScreen() {

    @Composable
    override fun Content() {
        Navigator(ContactDetailScreen(contactId))
    }
}