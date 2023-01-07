package com.example.marvelexpert.ui.screens.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.marvelexpert.R
import com.example.marvelexpert.data.entities.MarvelItem

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsListScreen(items: List<T>, onClick: (T) -> Unit) {
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
            items(items) {
                MarvelListItem(
                    marvelItem = it,
                    modifier = Modifier.clickable { onClick(it) }
                )
            }
        }
    }
}