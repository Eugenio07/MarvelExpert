package com.example.marvelexpert

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import arrow.core.Either
import coil.annotation.ExperimentalCoilApi
import com.example.marvelexpert.data.entities.Comic
import com.example.marvelexpert.ui.screens.common.MarvelItemsListScreen

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.util.*


class MarvelItemsListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val ctx = InstrumentationRegistry.getInstrumentation().targetContext

    private val items: List<Comic> = (1..100).map {
        Comic(
            id = it,
            title = "Title $it",
            description = "Description $it",
            thumbnail = "",
            format = Comic.Format.COMIC,
            urls = emptyList(),
            references = emptyList()
        )
    }

    @OptIn(ExperimentalFoundationApi::class, ExperimentalCoilApi::class)
    @Before
    fun setUp() {
        composeTestRule.setContent {
            MarvelItemsListScreen(items = Either.Right(items), onClick = {})
        }
    }

    @Test
    fun navigateTo51(): Unit = with(composeTestRule){
        onNode(hasScrollToIndexAction()).performScrollToIndex(50)
        onNodeWithText("Title 51").assertExists()
    }
    @Test
    fun navigatesTo51AndShowsBottomSheet(): Unit = with(composeTestRule){
        onNode(hasScrollToIndexAction()).performScrollToIndex(50)
        onNode(
            hasParent(hasText("Title 51")) and
                    hasContentDescription(ctx.getString(R.string.more_actions))
        ).performClick()

        onNode(
            hasAnySibling(hasText(ctx.getString(R.string.go_to_detail))) and
                    hasText("Title 51")
        ).assertExists()
    }
}