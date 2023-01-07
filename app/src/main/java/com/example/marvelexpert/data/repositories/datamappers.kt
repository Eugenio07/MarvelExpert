package com.example.marvelexpert.data.repositories

import com.example.marvelexpert.data.entities.*
import com.example.marvelexpert.data.network.entities.ApiComic
import com.example.marvelexpert.data.network.entities.ApiResponse
import com.example.marvelexpert.data.network.entities.ApiResponse.Data.ApiCharacter.ApiReferenceList
import com.example.marvelexpert.data.network.entities.asString


fun ApiResponse.Data.ApiCharacter.asCharacter(): Character = Character(
    id,
    name,
    description,
    thumbnail.asString(),
    listOf(
        comics.toDomain(ReferenceList.Type.COMIC),
        events.toDomain(ReferenceList.Type.EVENT),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls.map { Url(it.type, it.url) }
)

fun ApiComic.asComic(): Comic = Comic(
    id,
    title,
    description ?: "",
    thumbnail.asString(),
    format.toDomain(),
    listOf(
        characters.toDomain(ReferenceList.Type.CHARACTER),
        events.toDomain(ReferenceList.Type.EVENT),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls.map { Url(it.type, it.url) }
)

private fun String.toDomain(): Comic.Format = when (this) {
    "magazine" -> Comic.Format.MAGAZINE
    "trade paperback" -> Comic.Format.TRADE_PAPERBACK
    "hardcover" -> Comic.Format.HARDCOVER
    "digest" -> Comic.Format.DIGEST
    "graphic novel" -> Comic.Format.GRAPHIC_NOVEL
    "digital comic" -> Comic.Format.DIGITAL_COMIC
    "infinite comic" -> Comic.Format.INFINITE_COMIC
    else -> Comic.Format.COMIC
}

fun Comic.Format.toStringFormat(): String = when (this) {
    Comic.Format.COMIC -> "comic"
    Comic.Format.MAGAZINE -> "magazine"
    Comic.Format.TRADE_PAPERBACK -> "trade paperback"
    Comic.Format.HARDCOVER -> "hardcover"
    Comic.Format.DIGEST -> "digest"
    Comic.Format.GRAPHIC_NOVEL -> "graphic novel"
    Comic.Format.DIGITAL_COMIC -> "digital comic"
    Comic.Format.INFINITE_COMIC -> "infinite comic"
}

private fun ApiReferenceList.toDomain(type: ReferenceList.Type): ReferenceList {
    return ReferenceList(
        type,
        items
            .let { items.map { Reference(it.name) } }
    )
}