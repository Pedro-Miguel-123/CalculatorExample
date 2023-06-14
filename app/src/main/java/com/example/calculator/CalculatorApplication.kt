package com.example.calculator

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CalculatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.println(Log.DEBUG, "s","reached applicatiion")
    }
}