package com.example.marvelexpert.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.marvelexpert.R
import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.data.entities.MarvelItem
import com.example.marvelexpert.ui.MarvelScreen

@Composable
fun <T : MarvelItem> MarvelItemBottomPreview(item: T?, onGoToDetail: (T) -> Unit) {
    if (item != null) {

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.thumbnail),
                contentDescription = item.title,
                modifier = Modifier
                    .width(96.dp)
                    .aspectRatio(1 / 1.5f)
                    .background(Color.LightGray)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = item.title, style = MaterialTheme.typography.titleLarge)
                Text(text = item.description)
                Button(
                    onClick = { onGoToDetail(item) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = stringResource(id = R.string.go_to_detail))
                }
            }
        }
    } else {
        Spacer(modifier = Modifier.height(1.dp))
    }

}

@Preview
@Composable
fun MarvelItemBottomPreview2() {
    MarvelScreen {
        MarvelItemBottomPreview(item = Character(1,
            "Ejemplo 1",
            "wedwedwedwedwedwe dfsdf s rfrgrgafasd  edfsd fsdf sg v",
            "",
            emptyList(),
            emptyList())) {}
    }

}