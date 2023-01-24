package com.example.marvelexpert.data.repositories

import com.example.marvelexpert.data.entities.Comic
import com.example.marvelexpert.data.entities.Result
import com.example.marvelexpert.data.entities.tryCall
import com.example.marvelexpert.data.network.remote.ComicsService
import javax.inject.Inject

class ComicsRepository @Inject constructor(private val service: ComicsService) {

    suspend fun get(format: Comic.Format): Result<List<Comic>> = tryCall {
        service
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .map { it.asComic() }
    }


    suspend fun find(id: Int): Result<Comic> = tryCall {
        service
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }
}