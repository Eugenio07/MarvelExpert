package com.example.marvelexpert.ui.screens.common

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.marvelexpert.R
import com.example.marvelexpert.data.entities.MarvelItem
import com.example.marvelexpert.data.entities.Reference
import com.example.marvelexpert.data.entities.ReferenceList

@Composable
fun MarvelItemDetailScreen(
    marvelItem: MarvelItem,
    onUpClick: () -> Unit
) {
    MarvelItemDetailScaffold(
        marvelItem = marvelItem,
        onUpClick = onUpClick
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            item {
                Header(marvelItem)
            }
            marvelItem.references.forEach {
                val (icon, @StringRes stringRes) = it.type.createUiData()
                section(icon, stringRes, it.references)
            }
        }
    }
}

@Composable
fun Header(marvelItem: MarvelItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberAsyncImagePainter(model = marvelItem.thumbnail),
            contentDescription = marvelItem.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = marvelItem.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = marvelItem.description,
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

private fun ReferenceList.Type.createUiData(): Pair<ImageVector, Int> = when (this) {
    ReferenceList.Type.CHARACTER -> Icons.Default.Person to R.string.characters
    ReferenceList.Type.COMIC -> Icons.Default.Book to R.string.comics
    ReferenceList.Type.STORY -> Icons.Default.Bookmark to R.string.stories
    ReferenceList.Type.EVENT -> Icons.Default.Event to R.string.events
    ReferenceList.Type.SERIES -> Icons.Default.Collections to R.string.series
}
