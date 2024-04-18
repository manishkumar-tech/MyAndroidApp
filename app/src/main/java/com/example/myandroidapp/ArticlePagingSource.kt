package com.example.myandroidapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArticlePagingSource @Inject constructor(
    private val repository: ArticleRepository
) : PagingSource<Int, ArticlePojo>() {
    override fun getRefreshKey(state: PagingState<Int, ArticlePojo>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlePojo> {
        val page = params.key ?: 1
        val client_id = "hXrd9Cya48mPNbH3h7FaMIAvMj6qpiPwjsR-6BxFAyo"
        val response = repository.getDogs(client_id,page, params.loadSize)
        return try {
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(
                e
            )
        } catch (e: HttpException) {
            LoadResult.Error(
                e
            )
        }
    }
}