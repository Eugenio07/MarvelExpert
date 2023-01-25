package com.example.marvelexpert.ui.navigation

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun AppBottomNavigation(
    bottomNavOptions: List<NavItem>,
    currentRoute: String,
    onNavItemClick: (NavItem) -> Unit
) {
    MarvelBottomNavigation {
        bottomNavOptions.forEach { item ->
            val title = stringResource(id = item.title)
            BottomNavigationItem(
                selected = currentRoute.contains(item.navCommand.feature.route),
                onClick = { onNavItemClick(item) },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = title)
                },
                label = { Text(text = title) }
            )
        }
    }
}