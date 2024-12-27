package com.example.core

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.uniqueScreenKey

abstract class ContactUniqueScreen : Screen {
    override val key = uniqueScreenKey
}