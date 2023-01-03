package com.example.marvelexpert.data

import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.data.network.ApiClient
import com.example.marvelexpert.data.network.entities.asString

class CharactersRepository {

    suspend fun getCharacters(): List<Character> {
        val response = ApiClient.charactersService.getCharacters(100, 100)
        return response.data.results.map {
            Character(it.id, it.name, it.description, it.thumbnail.asString())
        }
    }
}