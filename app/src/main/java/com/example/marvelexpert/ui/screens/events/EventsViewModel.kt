package com.example.marvelexpert.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.example.marvelexpert.data.entities.Event
import com.example.marvelexpert.data.entities.Result
import com.example.marvelexpert.data.repositories.EventsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventsViewModel : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(events = EventsRepository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val events: Result<List<Event>> = emptyList<Event>().right()
    )
}