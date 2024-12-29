package com.example.contacts.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.contacts.presentation.ContactUiModel
import com.example.core.ContactUniqueScreen
import com.example.core.ds.ContactCircularProgressBar
import com.example.core.ds.ContactTopAppBar
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.contact_detail_title
import kontakt.composeapp.generated.resources.contact_detail_updated_success
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

class ContactDetailScreen(private val contactId: Long? = null) : ContactUniqueScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    ContactTopAppBar(
                        title = stringResource(Res.string.contact_detail_title),
                        onBackClick = {
                            navigator.pop()
                        }
                    )
                }
            },
        ) { paddingContent ->
            val viewModel: ContactDetailViewModel = getScreenModel()
            val screenState by viewModel.state.collectAsState()
            val screenEffects by viewModel.effect.collectAsState(null)
            val snackBarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()
            Box(modifier = Modifier.padding(paddingContent)) {
                if (screenState.isLoading) {
                    ContactCircularProgressBar()
                } else {
                    ContactDetailContent(
                        contactUiModel = screenState.contact ?: ContactUiModel(),
                        isLoading = screenState.isLoading,
                        updateState = { newContact ->
                            viewModel.updateState(screenState.copy(contact = newContact))
                        },
                        onUpdateContact = { contactId, contact ->
                            viewModel.dispatchContact(contactId, contact)
                        },
                        onRequestPhoto = {

                        },
                        onDeleteContact = { contactId ->
                            viewModel.deleteContact(contactId)
                        }
                    ) { contact ->
                        viewModel.dispatchContact(contact = contact)
                    }
                }
                SnackbarHost(
                    hostState = snackBarHostState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp)
                ) {
                    Snackbar(it, backgroundColor = Color.Green.copy(green = .8f))
                }
            }
            val contactDetailUpdateMessageSuccess =
                stringResource(Res.string.contact_detail_updated_success)
            screenEffects?.let { effect ->
                when (effect) {
                    ContactDetailUiEffects.GoBack -> LaunchedEffect(false) {
                        navigator.pop()
                    }

                    ContactDetailUiEffects.ShowUpdateSuccess -> LaunchedEffect(false) {
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = contactDetailUpdateMessageSuccess,
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                }
            }
            LaunchedEffect(false) {
                viewModel.getContactIfRequired(contactId)
            }
        }
    }
}