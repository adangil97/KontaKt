package com.example

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.contacts.presentation.list.ContactListScreen
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.global_navigator_key
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(
            ContactListScreen(),
            key = stringResource(Res.string.global_navigator_key)
        )
    }
}