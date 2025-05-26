package com.example.bus_schedule_app.screens.busstop

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bus_schedule_app.data.models.Schedule
import com.example.bus_schedule_app.data.repositories.ScheduleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BusStopViewModel(
    savedStateHandle: SavedStateHandle,
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {
    private val busStopName: String = checkNotNull(savedStateHandle[BusStopDestination.busStopNameArgs])

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val busStopUiState: StateFlow<BusStopUiState> =
        scheduleRepository.getSchedulesByBusNameStream(busStopName).map { BusStopUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = BusStopUiState()
            )
}

data class BusStopUiState(val fullSchedulesList: List<Schedule> = listOf())