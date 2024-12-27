package com.example.core

import com.example.contacts.ContactDatabaseQueries
import org.koin.dsl.module

val appModules = module {
    single { ContactDatabaseQueries(createDriver()) }
}