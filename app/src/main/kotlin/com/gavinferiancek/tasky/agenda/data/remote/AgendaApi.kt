package com.gavinferiancek.tasky.agenda.data.remote

import com.gavinferiancek.tasky.agenda.data.remote.list.AgendaResponseDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query

interface AgendaApi {

    @GET("/agenda")
    suspend fun getAgenda(
        @Query("timezone") timeZone: String,
        @Query("time") time: Long,
    ): AgendaResponseDto

    @DELETE("/event")
    suspend fun deleteEvent(@Query("eventId") id: String)

    @DELETE("/task")
    suspend fun deleteTask(@Query("eventId") id: String)

    @DELETE("/reminder")
    suspend fun deleteReminder(@Query("eventId") id: String)
}