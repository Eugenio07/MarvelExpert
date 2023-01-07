package com.example.marvelexpert.data.repositories

import com.antonioleiva.marvelcompose.data.repositories.Repository
import com.example.marvelexpert.data.entities.Character
import com.example.marvelexpert.data.entities.Reference
import com.example.marvelexpert.data.entities.Url
import com.example.marvelexpert.data.network.ApiClient
import com.example.marvelexpert.data.network.entities.ApiResponse.Data.ApiCharacter
import com.example.marvelexpert.data.network.entities.asString

object CharactersRepository : Repository<Character>() {

    suspend fun get(): List<Character> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }


    suspend fun find(id: Int): Character = super.find(
        findActionLocal = { it.id == id },
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