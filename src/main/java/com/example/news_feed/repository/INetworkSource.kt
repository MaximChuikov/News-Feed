package com.example.news_feed.repository

import com.example.news_feed.models.UsersModel
import com.example.news_feed.models.UsersResponse
import retrofit2.Response

interface INetworkRequest {
    suspend fun sendData(): Response<UsersResponse>
}
