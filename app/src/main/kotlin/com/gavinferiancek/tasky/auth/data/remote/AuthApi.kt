package com.gavinferiancek.tasky.auth.data.remote

import com.gavinferiancek.tasky.auth.data.remote.login.LoginRequestDto
import com.gavinferiancek.tasky.auth.data.remote.login.LoginResponseDto
import com.gavinferiancek.tasky.auth.data.remote.register.RegisterRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequestDto): LoginResponseDto

    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequestDto)

    @GET("/authenticate")
    suspend fun authenticateToken()
}