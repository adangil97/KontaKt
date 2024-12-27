package com.example

import android.app.Application
import com.example.core.initKoin
import org.koin.android.ext.koin.androidContext

class ContactApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ContactApp)
        }
    }
}