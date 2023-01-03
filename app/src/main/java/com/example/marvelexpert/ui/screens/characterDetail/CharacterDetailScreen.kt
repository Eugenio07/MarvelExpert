package com.example.marvelexpert.ui.screens.characterDetail

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Event
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.marvelexpert.MarvelApp
import com.example.marvelexpert.R
import com.example.marvelexpert.data.CharactersRepository
import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.data.entities.Reference
import com.example.marvelexpert.data.entities.Url
import com.example.marvelexpert.ui.navigation.ArrowBackIcon

@Composable
fun CharacterDetailScreen(characterId: Int, onUpClick: () -> Unit) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.findCharacter(characterId)

    }
    characterState?.let {
        CharacterDetailScreen(it, onUpClick)
    }
}

@Composable
fun CharacterDetailScreen(character: Character, onUpClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(character.name) },
                navigationIcon = { ArrowBackIcon(onUpClick) }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            item {
                Header(character)
            }
            section(Icons.Default.Collections, R.string.series, character.series)
            section(Icons.Default.Event, R.string.events, character.events)
            section(Icons.Default.Book, R.string.comics, character.comics)
            section(Icons.Default.Bookmark, R.string.stories, character.stories)
        }
    }
}

@Composable
fun Header(character: Character) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberAsyncImagePainter(model = character.thumbnail),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun LazyListScope.section(icon: ImageVector, @StringRes name: Int, items: List<Reference>) {
    if (items.isEmpty()) return

    item {
        Text(
            text = stringResource(name),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items) {
        ListItem(
            icon = { Icon(icon, contentDescription = null) },
            text = { Text(it.name) }
        )
    }
}

@Preview(widthDp = 393, heightDp = 851)
@Composable
fun CharacterDetailScreenPreview() {
    val c = Character(
        1,
        "Iron Man",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras placerat dolor ut leo egestas molestie. Nam aliquet varius enim, vel placerat arcu bibendum ac. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam eu ultricies augue. Donec nulla orci, commodo nec dui nec, congue molestie mauris. Nulla ac nulla vel nulla auctor cursus. Praesent dui enim, aliquet at lacinia in, porttitor vitae mi. Phasellus lobortis mauris nec sapien luctus, placerat molestie leo condimentum. Sed eu nibh at tellus auctor vulputate ullamcorper et risus. Phasellus elementum magna in nisl mattis pellentesque. Pellentesque vel erat a urna pharetra auctor. Etiam molestie sit amet dolor nec accumsan. Fusce non quam cursus, venenatis risus at, euismod nisi. Nullam finibus, orci sit amet bibendum maximus, metus augue dictum ipsum, quis hendrerit nulla metus ac ante.",
        "",
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Url("Comic 1", "url 1"), Url("Comic 2", "url 2"))
    )

    MarvelApp {
        CharacterDetailScreen(character = c, onUpClick = {})
    }
}