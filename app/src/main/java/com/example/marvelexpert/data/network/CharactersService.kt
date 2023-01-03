package com.example.marvelexpert.data.network


import com.example.marvelexpert.data.network.entities.ApiResponse
import com.example.marvelexpert.data.network.entities.ApiResponse.Data.*
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<ApiCharacter>
}