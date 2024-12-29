package com.example.contacts.framework

import com.example.contacts.data.ContactRepository
import com.example.contacts.presentation.detail.ContactDetailViewModel
import com.example.contacts.usecases.DeleteContactById
import com.example.contacts.usecases.GetContactById
import com.example.contacts.usecases.SaveContact
import com.example.contacts.usecases.SearchContacts
import com.example.contacts.usecases.UpdateContact
import org.koin.dsl.module

val contactModule = module {
    single<ContactRepository> { DBContactRepository(get()) }
    single { DeleteContactById(get()) }
    single { GetContactById(get(), get()) }
    single { com.example.contacts.usecases.GetContacts(get()) }
    single { SaveContact(get(), get(), get()) }
    single { SearchContacts(get()) }
    single { UpdateContact(get()) }
    factory { ContactDetailViewModel(get(), get(), get(), get(), get(), get()) }
    factory { com.example.contacts.presentation.list.ContactListViewModel(get(), get()) }
}