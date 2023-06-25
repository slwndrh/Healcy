package com.example.healcy.monitoring

import android.app.Application
import com.firebase.client.Firebase

class FireApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.setAndroidContext(this)
    }
}