package com.gavinferiancek.tasky.agenda.data.remote

import com.gavinferiancek.tasky.agenda.data.remote.list.AgendaResponseDto
import com.gavinferiancek.tasky.agenda.data.remote.shared.TaskRequestDto
import retrofit2.http.*

interface AgendaApi {

    @GET("/agenda")
    suspend fun getAgenda(
        @Query("timezone") timeZone: String,
        @Query("time") timestamp: Long,
    ): AgendaResponseDto

    @DELETE("/event")
    suspend fun deleteEvent(@Query("eventId") id: String)

    @PUT("/task")
    suspend fun updateTask(@Body taskRequest: TaskRequestDto)

    @DELETE("/task")
    suspend fun deleteTask(@Query("taskId") id: String)

    @DELETE("/reminder")
    suspend fun deleteReminder(@Query("reminderId") id: String)
}