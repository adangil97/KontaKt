package com.example.contacts.framework

import com.example.contacts.data.ContactRepository
import com.example.contacts.usecases.DeleteContactById
import com.example.contacts.usecases.GetContactById
import com.example.contacts.usecases.GetContacts
import com.example.contacts.usecases.SaveContact
import com.example.contacts.usecases.SearchContacts
import com.example.contacts.usecases.UpdateContact
import org.koin.dsl.module

val contactModule = module {
    single<ContactRepository> { ContactLocalRepository(get()) }
    single { DeleteContactById(get()) }
    single { GetContactById(get()) }
    single { GetContacts(get()) }
    single { SaveContact(get()) }
    single { SearchContacts(get()) }
    single { UpdateContact(get()) }
}