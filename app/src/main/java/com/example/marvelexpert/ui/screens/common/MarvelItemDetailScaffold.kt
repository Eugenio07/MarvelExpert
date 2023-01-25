package com.example.marvelexpert.ui.screens.common

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.example.marvelexpert.R
import com.example.marvelexpert.data.entities.MarvelItem
import com.example.marvelexpert.data.entities.Url
import com.example.marvelexpert.ui.navigation.AppBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelItemDetailScaffold(
    marvelItem: MarvelItem,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            if (marvelItem.urls.isNotEmpty()) {
                FloatingActionButton(onClick = {
                    shareCharacter(
                        context,
                        marvelItem.title,
                        marvelItem.urls.first()
                    )
                }) {
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

private fun shareCharacter(context: Context, name: String, url: Url) {
    val intent = ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(name)
        .setText(url.destination)
        .intent
    context.startActivity(intent)
}