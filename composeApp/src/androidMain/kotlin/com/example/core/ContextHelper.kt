package com.example.core

import android.content.Context
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// Injection Boostrap Helper
class ContextHelper : KoinComponent {
    private val context: Context by inject()
    fun context(): Context = context
}