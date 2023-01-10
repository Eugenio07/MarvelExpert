package com.example.marvelexpert.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marvelexpert.ui.navigation.AppBottomNavigation
import com.example.marvelexpert.ui.navigation.Navigation
import com.example.marvelexpert.ui.navigation.navigatePoppingUpToStartDestination
import com.example.marvelexpert.ui.theme.MarvelExpertTheme

@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    MarvelScreen {
        Scaffold(
            bottomBar = {
                AppBottomNavigation(currentRoute = currentRoute, onNavItemClick = {
                    navController.navigatePoppingUpToStartDestination(it.navCommand.route)
                })
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController)
            }
        }
    }
}

@Composable
fun MarvelScreen(content: @Composable () -> Unit) {
    MarvelExpertTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}