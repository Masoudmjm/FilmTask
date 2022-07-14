package com.masoudjafari.filmnet.data

import com.masoudjafari.filmnet.data.model.SearchResponse
import com.masoudjafari.filmnet.data.remote.RetrofitService
import retrofit2.Response

class Repository constructor(private val retrofitService: RetrofitService) : DataSource  {
    override suspend fun getSearchResult(query: String): Response<SearchResponse> {
        return retrofitService.getSearchResult(query)
    }
}