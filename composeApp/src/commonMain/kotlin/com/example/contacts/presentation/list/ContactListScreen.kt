package com.example.contacts.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.contacts.presentation.detail.ContactDetailNavigationScreen
import com.example.core.ActionsScreen
import com.example.core.ContactUniqueScreen
import com.example.core.ds.ContactSearchBar
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.search_hint
import org.jetbrains.compose.resources.stringResource

class ContactListScreen : ContactUniqueScreen() {

    @Composable
    override fun Content() {

        val viewModel: ContactListViewModel = getScreenModel()
        val screenState by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            modifier = Modifier.padding(top = 12.dp),
            topBar = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    ContactSearchBar(
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .align(Alignment.CenterHorizontally),
                        hint = stringResource(Res.string.search_hint),
                        initialText = screenState.query,
                        onSearch = { query, _ ->
                            viewModel.sendAction(ActionsScreen(searchQuery = query))
                        }
                    )
                }
            },
        ) { paddingContent ->
            Box(modifier = Modifier.padding(paddingContent)) {
                if (screenState.isLoading) {
                    ContactListLoading()
                } else {
                    ContactListContent(
                        contacts = screenState.contacts,
                        modifier = Modifier.fillMaxSize().padding(8.dp),
                        onContactClick = { contactId ->
                            navigator.push(ContactDetailNavigationScreen(contactId))
                        }
                    )
                }
            }
        }
        LaunchedEffect(false) {
            viewModel.initialize()
        }
    }
}