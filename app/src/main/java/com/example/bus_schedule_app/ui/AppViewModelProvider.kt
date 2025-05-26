package com.example.bus_schedule_app.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bus_schedule_app.ScheduleApplication
import com.example.bus_schedule_app.screens.busstop.BusStopViewModel
import com.example.bus_schedule_app.screens.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(scheduleApplication().container.scheduleRepository)
        }

        initializer {
            BusStopViewModel(this.createSavedStateHandle(), scheduleApplication().container.scheduleRepository)
        }
    }
}

fun CreationExtras.scheduleApplication(): ScheduleApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ScheduleApplication)