package com.example.core

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModules)
}

// Suppress because is used by ios, compiler not recognized it.
@Suppress("unused")
fun initKoin() = initKoin {}