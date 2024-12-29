package com.example.core

import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator

@OptIn(InternalVoyagerApi::class)
fun Navigator?.findNavigatorByKey(key: String): Navigator? {
    return if (this?.parent == null || this.key == key) {
        this
    } else {
        this.parent.findNavigatorByKey(key)
    }
}