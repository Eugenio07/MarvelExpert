package com.example.marvelexpert.ui.screens.common

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.marvelexpert.data.entities.MarvelItem
import com.example.marvelexpert.data.entities.Result
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsListScreen(
    loading: Boolean = false,
    items: Result<List<T>>,
    onClick: (T) -> Unit,
) {
    items.fold({ ErrorMessage(error = it) }) {
        var bottomSheetItem by remember { mutableStateOf<T?>(null) }
        val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()

        BackPressedHandler {
            scope.launch { sheetState.hide() }
        }

        ModalBottomSheetLayout(
            sheetContent = {
                MarvelItemBottomPreview(
                    bottomSheetItem,
                    onGoToDetail = {
                        scope.launch {
                            sheetState.hide()
                            onClick(it)
                        }
                    }
                )
            },
            sheetState = sheetState
        ) {
            MarvelItemList(
                loading = loading,
                items = it,
                onClick = onClick,
                onItemMore = {
                    bottomSheetItem = it
                    scope.launch { sheetState.show() }
                }
            )
        }
    }
}

@Composable
fun BackPressedHandler(onBack: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val backDispatcher =
        requireNotNull(LocalOnBackPressedDispatcherOwner.current).onBackPressedDispatcher

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
    }

    DisposableEffect(lifecycleOwner, backDispatcher) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose { backCallback.remove() }
    }
}

@ExperimentalCoilApi
@Composable
fun <T : MarvelItem> MarvelItemList(
    loading: Boolean,
    items: List<T>,
    onClick: (T) -> Unit,
    onItemMore: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator()
        }

        if (items.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(170.dp),
                contentPadding = PaddingValues(4.dp),
            ) {
                items(items) {
                    MarvelListItem(
                        marvelItem = it,
                        modifier = Modifier.clickable { onClick(it) },
                        onItemMore = onItemMore
                    )
                }
            }
        }

    }

}