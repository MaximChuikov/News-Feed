package com.example.news_feed.models
import com.google.gson.annotations.SerializedName

data class Support(
    val url: String,
    val text: String
)

data class UsersResponse(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val data: List<User>,
    val support: Support
)