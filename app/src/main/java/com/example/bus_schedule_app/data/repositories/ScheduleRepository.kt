package com.example.bus_schedule_app.data.repositories

import com.example.bus_schedule_app.data.models.Schedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getAllSchedulesStream(): Flow<List<Schedule>>
    fun getSchedulesByBusNameStream(busName: String): Flow<List<Schedule>>
}