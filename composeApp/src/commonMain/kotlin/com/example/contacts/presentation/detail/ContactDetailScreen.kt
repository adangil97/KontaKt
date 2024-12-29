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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.CaptureImageScreen
import com.example.core.ContactUniqueScreen
import com.example.core.ds.ContactCircularProgressBar
import com.example.core.ds.ContactSelectorBottomSheet
import com.example.core.ds.ContactTopAppBar
import com.example.core.findNavigatorByKey
import com.preat.peekaboo.image.picker.ResizeOptions
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.contact_detail_title
import kontakt.composeapp.generated.resources.contact_detail_updated_success
import kontakt.composeapp.generated.resources.global_navigator_key
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

class ContactDetailScreen(private val contactId: Long? = null) : ContactUniqueScreen() {

    @Composable
    override fun Content() {
        val contactDetailNavigation = LocalNavigator.currentOrThrow
        val navigatorKey = stringResource(Res.string.global_navigator_key)
        val navigator = contactDetailNavigation.findNavigatorByKey(navigatorKey)
        val viewModel: ContactDetailViewModel = contactDetailNavigation.getNavigatorScreenModel()
        val screenState by viewModel.state.collectAsState()
        val screenEffects by viewModel.effect.collectAsState(null)
        val scope = rememberCoroutineScope()
        val singleImagePicker = rememberImagePickerLauncher(
            selectionMode = SelectionMode.Single,
            scope = scope,
            resizeOptions = ResizeOptions(
                width = 250,
                height = 250
            ),
            onResult = { byteArrays ->
                val image = byteArrays.firstOrNull()
                viewModel.updatePhoto(image)
            }
        )
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    ContactTopAppBar(
                        title = stringResource(Res.string.contact_detail_title),
                        onBackClick = {
                            navigator?.pop()
                        }
                    )
                }
            },
        ) { paddingContent ->
            val snackBarHostState = remember { SnackbarHostState() }
            var showImageBottomSheet by remember { mutableStateOf(false) }
            Box(modifier = Modifier.padding(paddingContent)) {
                ContactDetailContent(
                    contactUiModel = screenState.contact,
                    isLoading = screenState.isLoading,
                    updateState = { newContact ->
                        viewModel.updateState(screenState.copy(contact = newContact))
                    },
                    onUpdateContact = { contactId, contact ->
                        viewModel.dispatchContact(contactId, contact)
                    },
                    onRequestPhoto = {
                        showImageBottomSheet = true
                    },
                    onDeleteContact = { contactId ->
                        viewModel.deleteContact(contactId)
                    }
                ) { contact ->
                    viewModel.dispatchContact(contact = contact)
                }
                if (screenState.isLoading) {
                    ContactCircularProgressBar()
                }
                if (showImageBottomSheet) {
                    ContactSelectorBottomSheet(
                        modifier = Modifier.fillMaxWidth(),
                        onGallerySelection = {
                            singleImagePicker.launch()
                        },
                        onCameraSelection = {
                            contactDetailNavigation.push(CaptureImageScreen())
                        }
                    ) {
                        showImageBottomSheet = false
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
                        navigator?.pop()
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
                if (screenState.alreadyInitialized.not()) {
                    viewModel.getContactIfRequired(contactId)
                }
            }
        }
    }
}