package com.example.marvelexpert.data.repositories

import com.antonioleiva.marvelcompose.data.repositories.Repository
import com.example.marvelexpert.data.entities.Comic
import com.example.marvelexpert.data.network.ApiClient

object ComicsRepository : Repository<Comic>() {

    suspend fun get(format: Comic.Format?): List<Comic> = super.get {
        ApiClient
            .comicsService
            .getComics(0, 100, format?.toStringFormat())
            .data
            .results
            .map { it.asComic() }
    }

    suspend fun find(id: Int): Comic = super.find(
        findActionLocal = { it.id == id },
        findActionRemote = {
            ApiClient
                .comicsService
                .findComic(id)
                .data
                .results
                .first()
                .asComic()
        }
    )
}