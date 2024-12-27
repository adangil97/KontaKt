package com.example.core

import com.example.contacts.ContactDatabase
import org.koin.dsl.module

val appModules = module {
    single<ContactDatabase> { ContactDatabase.invoke(createDriver()) }
}