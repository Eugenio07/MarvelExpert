package com.example.marvelexpert.data.network.entities

data class ApiResponse<T>(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: Data<T>
) {
    data class Data<T>(
        val offset: Int,
        val limit: Int,
        val total: Int,
        val count: Int,
        val results: List<T>
    ) {
        data class ApiCharacter(
            val id: Int,
            val name: String,
            val description: String,
            val modified: String,
            val thumbnail: Thumbnail,
            val resourceURI: String,
            val comics: ApiReferenceList,
            val events: ApiReferenceList,
            val series: ApiReferenceList,
            val stories: ApiReferenceList,
            val urls: List<ApuUrl>
        ) {
            data class Thumbnail(
                val path: String,
                val extension: String
            )

            data class ApiReferenceList(
                val available: Int,
                val collectionURI: String,
                val items: List<ApiReference>,
                val returned: Int
            ) {
                data class ApiReference(
                    val resourceURI: String,
                    val name: String
                )
            }

            data class ApuUrl(
                val type: String,
                val url: String
            )
        }
    }
}

fun ApiResponse.Data.ApiCharacter.Thumbnail.asString() = "$path.$extension".replace("http", "https")