package com.example.marvelexpert.ui.screens.characters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.data.entities.Result
import com.example.marvelexpert.data.repositories.CharactersRepository
import com.example.marvelexpert.ui.navigation.NavArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle, repository: CharactersRepository) : ViewModel() {
    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(character = repository.find(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val character: Result<Character?> = Either.Right(null)
    )
}