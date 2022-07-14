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

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getSearchResponse(query : String) {
        loading.postValue(true)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getSearchResult(query)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    searchResponse.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
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
}