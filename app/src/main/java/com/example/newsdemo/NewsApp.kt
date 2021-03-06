package com.example.newsdemo

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())
    }
}