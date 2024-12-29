package com.example.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.contacts.presentation.detail.ContactDetailViewModel
import com.example.core.ds.CameraOverlay
import com.example.core.ds.PermissionDenied
import com.preat.peekaboo.ui.camera.PeekabooCamera
import com.preat.peekaboo.ui.camera.rememberPeekabooCameraState

class CaptureImageScreen : ContactUniqueScreen() {

    @Composable
    override fun Content() {
        val contactDetailNavigation = LocalNavigator.currentOrThrow
        val viewModel: ContactDetailViewModel = contactDetailNavigation.koinNavigatorScreenModel()
        PermissionContent(
            deniedContent = {
                PermissionDenied(modifier = Modifier.fillMaxSize())
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                val state = rememberPeekabooCameraState(
                    onCapture = { photo ->
                        viewModel.updatePhoto(photo)
                        contactDetailNavigation.pop()
                    }
                )
                PeekabooCamera(
                    state = state,
                    modifier = Modifier.fillMaxSize(),
                    permissionDeniedContent = {
                        PermissionDenied(modifier = Modifier.fillMaxSize())
                    }
                )
                CameraOverlay(
                    isCapturing = state.isCapturing,
                    onBack = {
                        contactDetailNavigation.pop()
                    },
                    onCapture = { state.capture() },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}