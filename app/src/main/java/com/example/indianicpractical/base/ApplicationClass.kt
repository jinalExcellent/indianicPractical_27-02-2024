package com.example.indianicpractical.base

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: ApplicationClass? = null
        fun getApplicationContext(): Context? {
            return instance?.applicationContext
        }
    }
}