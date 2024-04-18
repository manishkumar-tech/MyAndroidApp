package com.example.myandroidapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articlePagingSource: ArticlePagingSource
) : ViewModel() {

    private val _articleResponse: MutableStateFlow<PagingData<ArticlePojo>> =
        MutableStateFlow(PagingData.empty())
    var articleResponse = _articleResponse.asStateFlow()
        private set

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                articlePagingSource
            }.flow.cachedIn(viewModelScope).collect {
                _articleResponse.value = it
            }
        }
    }

}