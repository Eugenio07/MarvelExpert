package com.example.marvelexpert.ui.screens.events

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.example.marvelexpert.data.entities.Event
import com.example.marvelexpert.data.repositories.EventsRepository
import com.example.marvelexpert.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelexpert.ui.screens.common.MarvelItemsListScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun EventsScreen(onClick: (Event) -> Unit, viewModel: EventsViewModel = viewModel()) {
    MarvelItemsListScreen(
        loading = viewModel.state.loading,
        items = viewModel.state.events,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun EventDetailScreen(viewModel: EventDetailViewModel = viewModel()) {
        MarvelItemDetailScreen(
            loading = viewModel.state.loading,
            marvelItem = viewModel.state.event
        )

}