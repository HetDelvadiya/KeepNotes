package com.awcindia.keepnotes.app

import android.app.Application

class MyApplication : Application() {
    companion object {
        private lateinit var instance: MyApplication

        fun getAppContext(): Application = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
