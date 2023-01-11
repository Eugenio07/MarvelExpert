package com.example.marvelexpert.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.marvelexpert.ui.screens.*

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ) {
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
        composable(NavCommand.ContentType(Feature.SETTINGS)) { Settings() }
    }
}

private fun NavGraphBuilder.charactersNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavCommand.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(onClick = { character ->
                navController.navigate(
                    NavCommand.ContentTypeDetail(Feature.CHARACTERS).createRoute(character.id)
                )
            })
        }

        composable(NavCommand.ContentTypeDetail(Feature.CHARACTERS)) { character ->
            val id = character.findArg<Int>(NavArg.ItemId)
            CharacterDetailScreen(characterId = id)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalCoilApi::class, ExperimentalMaterialApi::class)
private fun NavGraphBuilder.comicsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavCommand.ContentType(Feature.COMICS)) {
            ComicsScreen(
                onClick = { comic ->
                    navController.navigate(
                        NavCommand.ContentTypeDetail(Feature.COMICS).createRoute(comic.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentTypeDetail(Feature.COMICS)) {
            val id = it.findArg<Int>(NavArg.ItemId)
            ComicDetailScreen(comicId = id)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalCoilApi::class, ExperimentalMaterialApi::class)
private fun NavGraphBuilder.eventsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavCommand.ContentType(Feature.EVENTS)) {
            EventsScreen(
                onClick = { event ->
                    navController.navigate(
                        NavCommand.ContentTypeDetail(Feature.EVENTS).createRoute(event.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentTypeDetail(Feature.EVENTS)) {
            val id = it.findArg<Int>(NavArg.ItemId)
            EventDetailScreen(eventId = id)
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}