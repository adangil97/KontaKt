package com.example.core

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual fun createDriver(
    databaseName: String,
    schema: SqlSchema<QueryResult.Value<Unit>>
): SqlDriver {
    return AndroidSqliteDriver(
        schema,
        ContextHelper().context(),
        databaseName
    )
}