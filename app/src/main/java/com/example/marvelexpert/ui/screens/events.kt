package com.example.marvelexpert.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.example.marvelexpert.data.entities.Event
import com.example.marvelexpert.data.repositories.EventsRepository
import com.example.marvelexpert.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelexpert.ui.screens.common.MarvelItemsListScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun EventsScreen(onClick: (Event) -> Unit) {
    var eventsState by remember { mutableStateOf(emptyList<Event>()) }
    LaunchedEffect(Unit) {
        eventsState = EventsRepository.get()
    }
    MarvelItemsListScreen(
        items = eventsState,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun EventDetailScreen(eventId: Int, onUpClick: () -> Unit) {
    var eventState by remember { mutableStateOf<Event?>(null) }
    LaunchedEffect(Unit) {
        eventState = EventsRepository.find(eventId)
    }
    eventState?.let {
        MarvelItemDetailScreen(it, onUpClick)
    }
}