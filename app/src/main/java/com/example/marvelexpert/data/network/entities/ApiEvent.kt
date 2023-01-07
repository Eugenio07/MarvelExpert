package com.example.marvelexpert.data.network.entities

import com.example.marvelexpert.data.network.entities.ApiResponse.Data.ApiCharacter.*

data class ApiEvent(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail,
    val characters: ApiReferenceList,
    val comics: ApiReferenceList,
    val series: ApiReferenceList,
    val stories: ApiReferenceList,
    val urls: List<ApuUrl>,
    val start: String,
    val end: String,
    val previous: ApiReference,
    val next: ApiReference,
    val modified: String,
    val resourceURI: String
)