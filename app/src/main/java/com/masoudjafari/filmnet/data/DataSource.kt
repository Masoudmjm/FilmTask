package com.masoudjafari.filmnet.data

import com.masoudjafari.filmnet.data.model.SearchResponse
import retrofit2.Response

interface DataSource {
    suspend fun getSearchResult(query: String, offset: Int, count: Int): Response<SearchResponse>
}