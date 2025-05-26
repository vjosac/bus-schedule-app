package com.example.bus_schedule_app

import android.app.Application
import com.example.bus_schedule_app.data.AppContainer
import com.example.bus_schedule_app.data.AppDataContainer

class ScheduleApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}