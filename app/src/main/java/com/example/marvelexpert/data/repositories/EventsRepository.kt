package com.example.marvelexpert.data.repositories

import com.example.marvelexpert.data.entities.Event
import com.example.marvelexpert.data.entities.Result
import com.example.marvelexpert.data.network.ApiClient

object EventsRepository : Repository<Event>() {

    suspend fun get(): Result<List<Event>> = super.get {
        ApiClient
            .eventsService
            .getEvents(0, 100)
            .data
            .results
            .map { it.asEvent() }
    }

    suspend fun find(id: Int): Result<Event> = super.find(
        id,
        findActionRemote = {
            ApiClient
                .eventsService
                .findEvent(id)
                .data
                .results
                .first()
                .asEvent()
        }
    )
}