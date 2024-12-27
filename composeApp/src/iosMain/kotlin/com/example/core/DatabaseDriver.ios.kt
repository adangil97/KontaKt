package com.example.core

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.contacts.ContactDatabase

actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(ContactDatabase.Schema, "contact.db")
}