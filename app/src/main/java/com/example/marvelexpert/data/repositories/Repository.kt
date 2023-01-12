package com.example.marvelexpert.data.repositories

import com.example.marvelexpert.data.entities.MarvelItem
import com.example.marvelexpert.data.entities.Result
import com.example.marvelexpert.data.entities.tryCall

abstract class Repository<T : MarvelItem> {

    private var cache: List<T> = emptyList()

    internal suspend fun get(getAction: suspend () -> List<T>): Result<List<T>> = tryCall {
        if (cache.isEmpty()) {
            cache = getAction()
        }
        cache
    }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T
    ): Result<T> = tryCall {
        cache.find { it.id == id } ?: findActionRemote()
    }

}