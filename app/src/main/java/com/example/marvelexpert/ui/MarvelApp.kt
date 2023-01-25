package com.example.marvelexpert.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.marvelexpert.ui.navigation.MarvelTopAppBar
import com.example.marvelexpert.R
import com.example.marvelexpert.ui.navigation.AppBarIcon
import com.example.marvelexpert.ui.navigation.AppBottomNavigation
import com.example.marvelexpert.ui.navigation.DrawerContent
import com.example.marvelexpert.ui.navigation.Navigation
import com.example.marvelexpert.ui.theme.MarvelExpertTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MarvelApp(appState: MarvelAppState = rememberMarvelAppState()) {
    val scroolState = rememberTopAppBarState()
    val scrollBehavior =TopAppBarDefaults.enterAlwaysScrollBehavior(scroolState)
    MarvelScreen {
        ModalNavigationDrawer(
            drawerState = appState.drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    DrawerContent(
                        drawerOptions = MarvelAppState.DRAWER_OPTIONS,
                        selectedIndex = appState.drawerSelectedIndex,
                        onOptionClick = { appState.onDrawerOptionClick(it) }
                    )
                }
            }) {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    MarvelTopAppBar(
                        title = { Text(stringResource(id = R.string.app_name)) },
                        navigationIcon = {
                            if (appState.showUpNavigation) {
                                AppBarIcon(
                                    imageVector = Icons.Default.ArrowBack,
                                    onClick = { appState.onUpClick() }
                                )
                            } else {
                                AppBarIcon(
                                    imageVector = Icons.Default.Menu,
                                    onClick = { appState.onMenuClick() }
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                },
                bottomBar = {
                    if (appState.showBottomNavigation) {
                        AppBottomNavigation(
                            bottomNavOptions = MarvelAppState.BOTTOM_NAV_OPTIONS,
                            currentRoute = appState.currentRoute
                        ) { appState.onNavItemClick(it) }
                    }
                },
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Navigation(appState.navController)
                }
            }
        }
        SetStatusBarColorEffect()
    }
}

@Composable
private fun SetStatusBarColorEffect(
    color: Color = MaterialTheme.colorScheme.secondary,
    systemUiController: SystemUiController = rememberSystemUiController()
) {
    SideEffect {
        systemUiController.setStatusBarColor(color)
    }
}

@Composable
fun MarvelScreen(content: @Composable () -> Unit) {
    MarvelExpertTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}