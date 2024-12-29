package com.example.contacts.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.contacts.presentation.ContactUiModel
import com.example.core.ds.ContactButton
import com.example.core.ds.ContactEditableTag
import com.example.core.ds.ContactImage
import com.example.core.ds.ContactSpacer
import com.example.core.ds.ContactTextField
import com.example.core.ds.ContactTopShadow
import com.example.core.hideKeyboard
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.contact_detail_email
import kontakt.composeapp.generated.resources.contact_detail_last_name
import kontakt.composeapp.generated.resources.contact_detail_name
import kontakt.composeapp.generated.resources.contact_detail_name_is_empty
import kontakt.composeapp.generated.resources.contact_detail_phone
import kontakt.composeapp.generated.resources.contact_detail_phone_error
import kontakt.composeapp.generated.resources.msg_delete
import kontakt.composeapp.generated.resources.msg_edit
import kontakt.composeapp.generated.resources.msg_loading
import kontakt.composeapp.generated.resources.msg_save
import kontakt.composeapp.generated.resources.msg_update
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ContactDetailContent(
    contactUiModel: ContactUiModel,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    updateState: (ContactUiModel) -> Unit,
    onRequestPhoto: () -> Unit,
    onUpdateContact: (Long, ContactUiModel) -> Unit,
    onDeleteContact: (Long) -> Unit,
    onNewContact: (ContactUiModel) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize().clickable(
            interactionSource = MutableInteractionSource(),
            indication = null
        ) {
            hideKeyboard()
        }
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                var nameError: String? by remember { mutableStateOf(null) }
                var phoneError: String? by remember { mutableStateOf(null) }
                val msgEdit = stringResource(Res.string.msg_edit)
                contactUiModel.photo?.let { photo ->
                    ContactImage(
                        imageBitmap = photo.decodeToImageBitmap(),
                        modifier = Modifier
                            .padding(12.dp)
                            .size(100.dp),
                        onClick = onRequestPhoto
                    ) {
                        ContactEditableTag(msgEdit)
                    }
                } ?: ContactImage(
                    imageVector = Icons.Filled.ImageSearch,
                    modifier = Modifier
                        .padding(12.dp)
                        .size(100.dp),
                    onClick = onRequestPhoto
                ) {
                    ContactEditableTag(msgEdit)
                }
                ContactSpacer(12.dp)
                ContactTopShadow()
                ContactSpacer(12.dp)
                Column(modifier = Modifier.fillMaxWidth(.8f)) {
                    val contactNameIsEmpty = stringResource(Res.string.contact_detail_name_is_empty)
                    ContactTextField(
                        contactUiModel.name,
                        onValueChange = {
                            updateState(contactUiModel.copy(name = it))
                            nameError = if (it.isBlank()) contactNameIsEmpty else null
                        },
                        msgError = nameError,
                        labelValue = stringResource(Res.string.contact_detail_name)
                    )
                    ContactSpacer(8.dp)
                    ContactTextField(
                        contactUiModel.lastName.orEmpty(),
                        onValueChange = {
                            updateState(contactUiModel.copy(lastName = it))
                        },
                        labelValue = stringResource(Res.string.contact_detail_last_name)
                    )
                    ContactSpacer(8.dp)
                    val phoneSizeError = stringResource(Res.string.contact_detail_phone_error)
                    ContactTextField(
                        contactUiModel.phoneNumber.orEmpty(),
                        onValueChange = { value ->
                            val phoneNumber = value.filter { it.isDigit() }
                            updateState(contactUiModel.copy(phoneNumber = phoneNumber))
                            phoneError = if (phoneNumber.length != 10) phoneSizeError else null
                        },
                        msgError = phoneError,
                        labelValue = stringResource(Res.string.contact_detail_phone),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    ContactSpacer(8.dp)
                    ContactTextField(
                        contactUiModel.email.orEmpty(),
                        onValueChange = {
                            updateState(contactUiModel.copy(email = it))
                        },
                        labelValue = stringResource(Res.string.contact_detail_email)
                    )
                    ContactSpacer(12.dp)
                }
                ContactSpacer(bottom = 24.dp)
            }
            items(contactUiModel.notes.orEmpty()) {
                Text(it)
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            if (contactUiModel.id == 0L) {
                val buttonText = if (isLoading.not()) {
                    stringResource(Res.string.msg_save)
                } else {
                    stringResource(Res.string.msg_loading)
                }
                ContactButton(
                    text = buttonText,
                    enabled = isLoading.not(),
                    modifier = Modifier.fillMaxWidth(.7f)
                ) {
                    onNewContact(contactUiModel)
                }
            } else {
                val updateButtonText = if (isLoading.not()) {
                    stringResource(Res.string.msg_update)
                } else {
                    stringResource(Res.string.msg_loading)
                }
                ContactButton(
                    text = updateButtonText,
                    enabled = isLoading.not(),
                    modifier = Modifier.fillMaxWidth(.7f)
                ) {
                    onUpdateContact(contactUiModel.id, contactUiModel)
                }
                val deleteButtonText = if (isLoading.not()) {
                    stringResource(Res.string.msg_delete)
                } else {
                    stringResource(Res.string.msg_loading)
                }
                ContactButton(
                    text = deleteButtonText,
                    enabled = isLoading.not(),
                    isError = true,
                    modifier = Modifier.fillMaxWidth(.7f)
                ) {
                    onDeleteContact(contactUiModel.id)
                }
            }
        }
    }
}