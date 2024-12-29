package com.example.core

import app.cash.sqldelight.async.coroutines.synchronous
import com.example.contacts.AppDatabase
import org.koin.dsl.module

val appModules = module {
    single<AppDatabase> {
        AppDatabase.invoke(
            createDriver(
                schema = AppDatabase.Schema.synchronous(),
                databaseName = "contact.db"
            )
        )
    }
    single {
        (get() as AppDatabase).contactDatabaseQueries
    }
    single {
        (get() as AppDatabase).notesDatabaseQueries
    }
}