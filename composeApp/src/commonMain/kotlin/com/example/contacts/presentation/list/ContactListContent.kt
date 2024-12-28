package com.example.contacts.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contacts.presentation.ContactUiModel
import com.example.core.ds.ContactButton
import com.example.core.ds.ContactCard
import com.example.core.ds.ContactIcon
import com.example.core.ds.ContactImage
import com.example.core.ds.ContactShimmerComponent
import com.example.core.ds.ContactSimpleContent
import com.example.core.ds.ContactSpacer
import com.example.core.ds.ContactText
import kontakt.composeapp.generated.resources.Res
import kontakt.composeapp.generated.resources.contact_list_empty
import kontakt.composeapp.generated.resources.create_new_contact
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactListContent(
    contacts: List<ContactUiModel>,
    modifier: Modifier = Modifier,
    onContactClick: (Long?) -> Unit
) {
    Box(modifier = modifier) {
        val createContactText = stringResource(Res.string.create_new_contact)
        if (contacts.isNotEmpty()) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ContactSpacer(4.dp)
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(contacts.size, key = { contacts[it].id }) {
                        val item = contacts[it]
                        ContactItem(
                            contactUiModel = item,
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                                .clickable { onContactClick(item.id) }
                        )
                    }
                }
                ContactButton(
                    text = createContactText,
                    onClick = { onContactClick(null) },
                    modifier = Modifier.fillMaxWidth(.7f)
                )
            }
        } else {
            val emptyContactList = stringResource(Res.string.contact_list_empty)
            ContactSimpleContent(
                emptyContactList,
                buttonMsg = createContactText,
                onButtonClick = { onContactClick(null) },
                imageVector = Icons.Filled.SupervisedUserCircle
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ContactItem(
    contactUiModel: ContactUiModel,
    modifier: Modifier = Modifier
) {
    ContactCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            contactUiModel.photo?.let { photo ->
                ContactImage(
                    imageBitmap = photo.decodeToImageBitmap(),
                    modifier = Modifier.padding(start = 18.dp, end = 8.dp).size(60.dp)
                )
            } ?: ContactImage(
                imageVector = Icons.Filled.Image,
                modifier = Modifier.padding(start = 18.dp, end = 8.dp).size(60.dp)
            )
            ContactSpacer(4.dp)
            Divider(
                modifier = Modifier
                    .height(100.dp)
                    .width(1.dp),
                color = Color.LightGray,
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                ContactText(
                    contactUiModel.name,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                ContactSpacer(4.dp)
                ContactText(
                    contactUiModel.email.orEmpty(),
                    textAlign = TextAlign.Start,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                ContactSpacer(4.dp)
            }
            ContactIcon(
                imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(12.dp).size(24.dp),
            )
        }
    }
}

@Composable
fun ContactListLoading(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        ContactSpacer(4.dp)
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(20) {
                ContactItemShimmer(modifier = Modifier.padding(12.dp))
            }
        }
    }
}

@Composable
fun ContactItemShimmer(modifier: Modifier = Modifier) {
    ContactCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ContactShimmerComponent(
                modifier = Modifier
                    .padding(start = 18.dp, end = 8.dp)
                    .clip(CircleShape)
                    .size(60.dp)
            )
            ContactSpacer(4.dp)
            Divider(
                modifier = Modifier
                    .height(100.dp)
                    .width(1.dp),
                color = Color.LightGray,
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                ContactShimmerComponent(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(.7f)
                        .height(24.dp)
                )
                ContactSpacer(4.dp)
                ContactShimmerComponent(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(.9f)
                        .height(18.dp)
                )
                ContactSpacer(4.dp)
            }
            ContactShimmerComponent(
                modifier = Modifier
                    .padding(12.dp)
                    .size(12.dp)
            )
        }
    }
}