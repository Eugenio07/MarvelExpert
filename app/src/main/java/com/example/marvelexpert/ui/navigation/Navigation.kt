package com.example.marvelexpert.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelexpert.ui.screens.CharactersScreen
import com.example.marvelexpert.ui.screens.characterDetail.CharacterDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavItem.Characters.route) {
        composable(NavItem.Characters) {
            CharactersScreen(onClick = { character ->
                navController.navigate(NavItem.CharacterDetail.createRoute(character.id))
            })
        }

        composable(NavItem.CharacterDetail) { character ->
            val id = character.findArg<Int>(NavArg.ItemId)
            CharacterDetailScreen(
                characterId = id,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}