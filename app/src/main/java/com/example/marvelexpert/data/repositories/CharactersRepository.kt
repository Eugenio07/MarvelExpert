package com.example.marvelexpert.data.repositories

import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.data.entities.Result
import com.example.marvelexpert.data.network.ApiClient

object CharactersRepository : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }


    suspend fun find(id: Int): Result<Character> = super.find(
        id,
        findActionRemote = {
            ApiClient
                .charactersService
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )

}