package com.example.marvelexpert.ui.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.example.marvelexpert.data.entities.Event
import com.example.marvelexpert.data.entities.Result
import com.example.marvelexpert.data.repositories.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(repository: EventsRepository) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(events = repository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val events: Result<List<Event>> = emptyList<Event>().right()
    )
}