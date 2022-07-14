package com.masoudjafari.filmnet.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masoudjafari.filmnet.data.Repository
import com.masoudjafari.filmnet.data.model.SearchResponse
import kotlinx.coroutines.*

class MainViewModel constructor(private val repository: Repository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val searchResponse = MutableLiveData<SearchResponse>()
    val loading = MutableLiveData<Boolean>()
    private var lastOffset = 0
    private val count = 10
    private var searchPhrase = ""
    var newPhrase = false

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun getSearchResponse(query : String, offset: Int, count: Int) {
        loading.postValue(true)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getSearchResult(query, offset, count)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    searchResponse.postValue(response.body())
                    lastOffset += count
                }
                else
                    onError("Error : ${response.message()} ")

                loading.value = false
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun getSearchResult(searchPhrase : String) {
        if (this.searchPhrase == searchPhrase) {
            newPhrase = false
            getSearchResponse(searchPhrase, lastOffset, count)
        }
        else {
            newPhrase = true
            lastOffset = 0
            searchResponse.value?.data = emptyList()
            this.searchPhrase = searchPhrase
            getSearchResponse(searchPhrase, lastOffset, count)
        }
    }
}