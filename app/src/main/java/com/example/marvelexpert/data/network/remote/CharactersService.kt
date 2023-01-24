package com.example.marvelexpert.data.network.remote


import com.example.marvelexpert.data.network.entities.ApiResponse
import com.example.marvelexpert.data.network.entities.ApiResponse.Data.ApiCharacter
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<ApiCharacter>

    @GET("/v1/public/characters/{characterId}")
    suspend fun findCharacter(
        @Path("characterId") characterId: Int,
    ): ApiResponse<ApiCharacter>
}