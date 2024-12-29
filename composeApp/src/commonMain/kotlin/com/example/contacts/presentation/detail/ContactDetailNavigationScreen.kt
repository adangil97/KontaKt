package com.example.contacts.presentation.detail

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.core.ContactUniqueScreen

@OptIn(ExperimentalVoyagerApi::class)
class ContactDetailNavigationScreen(private val contactId: Long? = null) : ContactUniqueScreen() {

    @Composable
    override fun Content() {
        Navigator(
            ContactDetailScreen(contactId),
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
        ) { navigator ->
            SlideTransition(
                navigator = navigator,
                disposeScreenAfterTransitionEnd = true
            )
        }
    }
}