package com.dev_akash.freedictionary.core

import android.app.Application
import com.dev_akash.freedictionary.utils.preferences.SharedPrefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationKClass : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefs.loadAppPrefs(this)
    }
}