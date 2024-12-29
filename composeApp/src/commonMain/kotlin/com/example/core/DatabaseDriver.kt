package com.example.core

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema

expect fun createDriver(
    databaseName: String,
    schema: SqlSchema<QueryResult.Value<Unit>>
): SqlDriver