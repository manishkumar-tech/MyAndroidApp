package com.example.myandroidapp


import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.unsplash.com"
    }

    @GET("/photos")
    suspend fun getAllArticles(
        @Query("client_id") client_id: String,
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): List<ArticlePojo>
}