package com.example.bus_schedule_app.data

import android.content.Context
import com.example.bus_schedule_app.data.repositories.OfflineScheduleRepository
import com.example.bus_schedule_app.data.repositories.ScheduleRepository

interface AppContainer {
    val scheduleRepository: ScheduleRepository
}

class AppDataContainer(
    private val context: Context
) : AppContainer {
    override val scheduleRepository: ScheduleRepository by lazy {
        OfflineScheduleRepository(AppDatabase.getDatabase(context).scheduleDao())
    }
}