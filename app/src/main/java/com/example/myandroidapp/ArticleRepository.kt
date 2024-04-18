package com.example.myandroidapp


import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getDogs(
        client_id: String,
        page: Int,
        limit: Int
    ): List<ArticlePojo> = apiService.getAllArticles(
        client_id,
        page, limit
    )

}