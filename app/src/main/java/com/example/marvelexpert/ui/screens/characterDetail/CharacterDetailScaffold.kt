package com.example.marvelexpert.ui.screens.characterDetail

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.example.marvelexpert.R
import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.ui.navigation.ArrowBackIcon

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterDetailScaffold(
    character: Character,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(character.name) },
                navigationIcon = { ArrowBackIcon(onUpClick) },
                actions = { AppBarOverflowMenu(character.urls) }
            )
        },
        floatingActionButton = {
            if (character.urls.isNotEmpty()) {
                FloatingActionButton(onClick = { shareCharacter(context, character) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(R.string.share_character)
                    )
                }
            }
        },
        content = content
    )
}

private fun shareCharacter(context: Context, character: Character) {
    val intent = ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(character.name)
        .setText(character.urls.first().url)
        .intent
    context.startActivity(intent)
}