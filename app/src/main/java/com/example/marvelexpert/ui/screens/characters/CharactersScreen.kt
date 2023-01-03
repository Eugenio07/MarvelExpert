package com.example.marvelexpert.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.marvelexpert.MarvelApp
import com.example.marvelexpert.R
import com.example.marvelexpert.data.CharactersRepository
import com.example.marvelexpert.data.entities.Character

@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var charactersState by rememberSaveable { mutableStateOf(emptyList<Character>()) }
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.getCharacters()
    }
    CharactersScreen(
        characters = charactersState,
        onClick = onClick
    )
}

@Composable
fun CharactersScreen(characters: List<Character>, onClick: (Character) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(170.dp),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(characters) {
                CharacterItem(
                    character = it,
                    modifier = Modifier.clickable { onClick(it) }
                )
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Card {
            Image(
                painter = rememberAsyncImagePainter(model = character.thumbnail),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
        Text(
            text = character.name,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
        )
    }

}