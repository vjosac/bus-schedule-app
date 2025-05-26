package com.example.bus_schedule_app.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.bus_schedule_app.data.models.Schedule
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * from schedule ORDER BY arrival_time ASC")
    fun getAllSchedules(): Flow<List<Schedule>>

    @Query("SELECT * from schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    fun getSchedulesByStopName(stopName: String): Flow<List<Schedule>>
}