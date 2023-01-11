package com.example.marvelexpert.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.example.marvelexpert.data.entities.Comic
import com.example.marvelexpert.data.repositories.ComicsRepository
import com.example.marvelexpert.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelexpert.ui.screens.common.MarvelItemList
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by remember { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.get()
    }
    val formats = Comic.Format.values()
    HorizontalPager(
        count = formats.size
    ) {
        MarvelItemList(
            items = comicsState,
            onClick = onClick
        )
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun ComicDetailScreen(comicId: Int) {
    var comicState by remember { mutableStateOf<Comic?>(null) }
    LaunchedEffect(Unit) {
        comicState = ComicsRepository.find(comicId)
    }
    comicState?.let {
        MarvelItemDetailScreen(it)
    }
}