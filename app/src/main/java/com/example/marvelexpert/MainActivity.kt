package com.example.marvelexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.ui.navigation.Navigation
import com.example.marvelexpert.ui.screens.CharactersScreen
import com.example.marvelexpert.ui.theme.MarvelExpertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelApp {
                Navigation()
            }
        }
    }
}

@Composable
fun MarvelApp(content: @Composable () -> Unit){
    MarvelExpertTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}