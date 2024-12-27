package com.example.core

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.contacts.ContactDatabase

actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(ContactDatabase.Schema, ContextHelper().context(), "contact.db")
}