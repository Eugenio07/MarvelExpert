package com.example.marvelexpert.ui.screens.comics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelexpert.data.entities.Comic
import com.example.marvelexpert.data.repositories.ComicsRepository
import com.example.marvelexpert.ui.navigation.NavArg
import com.example.marvelexpert.ui.screens.events.EventDetailViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ComicDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(comic = ComicsRepository.find(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val comic: Comic? = null
    )
}