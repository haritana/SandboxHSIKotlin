package com.example.quiz1

import android.app.Application
import com.example.quiz1.data.AppContainer
import com.example.quiz1.data.DefaultAppContainer

class CampusApplication : Application() {
    lateinit var container : AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}