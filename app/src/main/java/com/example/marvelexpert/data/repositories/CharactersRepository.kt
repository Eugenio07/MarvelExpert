package com.example.marvelexpert.data.repositories

import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.data.entities.Result
import com.example.marvelexpert.data.network.ApiClient
import com.example.marvelexpert.data.network.remote.CharactersService
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val service: CharactersService) : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        service
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }


    suspend fun find(id: Int): Result<Character> = super.find(
        id,
        findActionRemote = {
            service
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )

}