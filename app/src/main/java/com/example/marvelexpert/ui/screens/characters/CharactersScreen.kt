package com.example.marvelexpert.ui.screens.characters


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.data.repositories.CharactersRepository
import com.example.marvelexpert.ui.screens.common.MarvelItemsListScreen

@OptIn(ExperimentalFoundationApi::class, ExperimentalCoilApi::class)
@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var charactersState by remember { mutableStateOf(emptyList<Character>()) }
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.get()
    }
    MarvelItemsListScreen(
        items = charactersState,
        onClick = onClick
    )
}