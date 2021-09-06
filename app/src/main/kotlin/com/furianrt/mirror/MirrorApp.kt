package com.furianrt.mirror

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class MirrorApp : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}