package com.example.marvelexpert.data.repositories

import com.example.marvelexpert.data.entities.Comic
import com.example.marvelexpert.data.entities.Result
import com.example.marvelexpert.data.entities.tryCall
import com.example.marvelexpert.data.network.ApiClient

object ComicsRepository {

    suspend fun get(format: Comic.Format): Result<List<Comic>> = tryCall {
        ApiClient
            .comicsService
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .map { it.asComic() }
    }


    suspend fun find(id: Int): Result<Comic> = tryCall {
        ApiClient
            .comicsService
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }
}