package com.example.marvelexpert.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marvelexpert.ui.navigation.NavItem
import com.example.marvelexpert.ui.navigation.navigatePoppingUpToStartDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberMarvelAppState(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MarvelAppState = remember(drawerState, navController, coroutineScope) {
    MarvelAppState(drawerState, navController, coroutineScope)
}

class MarvelAppState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val drawerState: DrawerState,
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
) {
    companion object {
        val DRAWER_OPTIONS = listOf(NavItem.HOME, NavItem.SETTINGS)
        val BOTTOM_NAV_OPTIONS = listOf(NavItem.CHARACTERS, NavItem.COMICS, NavItem.EVENTS)
    }

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    val showUpNavigation: Boolean
        @Composable get() = currentRoute !in NavItem.values().map { it.navCommand.route }

    val showBottomNavigation: Boolean
        @Composable get() = BOTTOM_NAV_OPTIONS.any { currentRoute.contains(it.navCommand.feature.route) }

    val drawerSelectedIndex: Int
        @Composable get() = if (showBottomNavigation) {
            DRAWER_OPTIONS.indexOf(NavItem.HOME)
        } else {
            DRAWER_OPTIONS.indexOfFirst { it.navCommand.route == currentRoute }
        }

    fun onUpClick() = navController.popBackStack()

    @OptIn(ExperimentalMaterial3Api::class)
    fun onMenuClick() = coroutineScope.launch { drawerState.open() }

    fun onNavItemClick(navItem: NavItem) =
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)

    @OptIn(ExperimentalMaterial3Api::class)
    fun onDrawerOptionClick(navItem: NavItem) {
        coroutineScope.launch { drawerState.close() }
        onNavItemClick(navItem)
    }
}