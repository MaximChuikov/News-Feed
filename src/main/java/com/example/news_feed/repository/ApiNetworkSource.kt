package com.example.news_feed.repository

import com.example.news_feed.models.UsersResponse
import com.example.news_feed.networks.MyRetrofitBuilder
import retrofit2.Response

class ApiNetworkResponse: INetworkRequest {
    override suspend fun sendData(): Response<UsersResponse> {
        return MyRetrofitBuilder.newsApi.getUsers()
    }
}
