package com.example.labs

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Deps.context = applicationContext
        Deps.initDatabase()
    }
}