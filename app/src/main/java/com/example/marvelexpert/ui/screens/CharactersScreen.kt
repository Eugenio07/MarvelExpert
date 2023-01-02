package com.example.marvelexpert.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.marvelexpert.MarvelApp
import com.example.marvelexpert.data.entities.Character

@Composable
fun CharactersScreen(characters: List<Character>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(170.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(characters) {
            CharacterItem(character = it)
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    Column(modifier = Modifier.padding(8.dp)) {
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

@Preview
@Composable
fun CharactersScreenPreview() {
    val characters = (1..10).map {
        Character(
            it,
            "Name $it",
            "Description",
            "https://via.placeholder.com/150x225/FFFF00/000000?text=name$it"
        )
    }
    MarvelApp {
        CharactersScreen(characters = characters)
    }
}