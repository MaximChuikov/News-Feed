package com.example.news_feed.networks

import com.example.news_feed.models.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface UsersAPI {
    @GET("users?page=1")
    suspend fun getUsers(): Response<UsersResponse>
}