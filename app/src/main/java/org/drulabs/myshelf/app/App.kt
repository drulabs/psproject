package org.drulabs.myshelf.app

import android.app.Application
import android.content.Context

class App: Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}