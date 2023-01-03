package com.example.marvelexpert.data

import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.data.entities.Reference
import com.example.marvelexpert.data.network.ApiClient
import com.example.marvelexpert.data.network.entities.ApiResponse.Data.ApiCharacter
import com.example.marvelexpert.data.network.entities.asString

object CharactersRepository {

    suspend fun getCharacters(): List<Character> {
        val response = ApiClient.charactersService.getCharacters(100, 100)
        return response.data.results.map {
            it.asCharacter()
        }
    }


    suspend fun findCharacter(characterId: Int): Character {
        val response = ApiClient.charactersService.findCharacter(characterId)
        return response.data.results.first().asCharacter()
    }

    private fun ApiCharacter.asCharacter(): Character {
        val comics = comics.items.map { Reference(it.name) }
        val events = events.items.map { Reference(it.name) }
        val series = series.items.map { Reference(it.name) }
        val stories = stories.items.map { Reference(it.name) }
        return Character(
            id,
            name,
            description,
            thumbnail.asString(),
            comics,
            events,
            series,
            stories
        )
    }
}