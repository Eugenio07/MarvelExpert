package com.example.marvelexpert.ui.screens.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.right
import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.data.entities.Result
import com.example.marvelexpert.data.repositories.CharactersRepository
import com.example.marvelexpert.ui.screens.events.EventDetailViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: CharactersRepository) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(characters = repository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val characters: Result<List<Character>> = emptyList<Character>().right()
    )
}