package com.example.core

import com.example.contacts.framework.contactModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModules)
    modules(contactModule)
}

// Suppress because is used by ios, compiler not recognized it.
@Suppress("unused")
fun initKoin() = initKoin {}