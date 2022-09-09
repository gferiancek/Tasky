package com.gavinferiancek.tasky.agenda.data.remote

import com.gavinferiancek.tasky.agenda.data.remote.list.AgendaResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AgendaApi {

    @GET("/agenda")
    suspend fun getAgenda(
        @Query("timezone") timeZone: String,
        @Query("time") timestamp: Long,
    ): AgendaResponseDto
}