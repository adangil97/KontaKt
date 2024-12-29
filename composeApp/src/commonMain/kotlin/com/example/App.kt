package com.example

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.contacts.presentation.list.ContactListScreen
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.global_navigator_key
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalVoyagerApi::class)
fun App() {
    MaterialTheme {
        Navigator(
            ContactListScreen(),
            key = stringResource(Res.string.global_navigator_key),
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
        ) { navigator ->
            SlideTransition(
                navigator = navigator,
                disposeScreenAfterTransitionEnd = true
            )
        }
    }
}