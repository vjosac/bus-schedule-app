package com.example.bus_schedule_app.data.repositories

import com.example.bus_schedule_app.data.dao.ScheduleDao
import com.example.bus_schedule_app.data.models.Schedule
import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(
    private val scheduleDao: ScheduleDao
) : ScheduleRepository {
    override fun getAllSchedulesStream(): Flow<List<Schedule>> = scheduleDao.getAllSchedules()

    override fun getSchedulesByBusNameStream(busName: String): Flow<List<Schedule>> = scheduleDao.getSchedulesByStopName(busName)
}