package com.kotlindemo

import android.app.Application
import com.kotlindemo.utils.preferences.ApplicationPreferences

class KotlinDemo : Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationPreferences.init(this)
    }

}