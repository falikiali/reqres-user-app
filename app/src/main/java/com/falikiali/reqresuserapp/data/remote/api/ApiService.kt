package com.falikiali.reqresuserapp.data.remote.api

import com.falikiali.reqresuserapp.data.remote.dto.LoginRequest
import com.falikiali.reqresuserapp.data.remote.dto.LoginResponse
import com.falikiali.reqresuserapp.data.remote.dto.UsersResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    suspend fun login(@Body req: LoginRequest): Response<LoginResponse>

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): UsersResponse

}