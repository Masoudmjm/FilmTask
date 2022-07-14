package com.masoudjafari.filmnet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.masoudjafari.filmnet.data.Repository
import com.masoudjafari.filmnet.data.model.DataItem
import com.masoudjafari.filmnet.data.remote.RetrofitService
import com.masoudjafari.filmnet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val searchAdapter = SearchAdapter()
    private var searchPhrase = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setObservers()
        setTextChangeListener()
    }

    private fun init() {
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = Repository(retrofitService)
        viewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository))[MainViewModel::class.java]
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = searchAdapter
        }
    }

    private fun setObservers() {
        viewModel.searchResponse.observe(this) {
            searchAdapter.setData(it.data as List<DataItem>, viewModel.newPhrase)
            searchAdapter.setCallback(object : SearchAdapter.SearchAdapterCallback {
                override fun loadMoreItems(position: Int) {
                    if (it.meta?.remainingItemsCount!! > 0) {
                        viewModel.getSearchResult(searchPhrase)
                    }
                }
            })
        }

        viewModel.loading.observe(this) {
            if (it)
                binding.progressDialog.visibility = VISIBLE
            else
                binding.progressDialog.visibility = GONE
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            Log.i("mytag", it.toString())
        }
    }

    private fun setTextChangeListener() {
        binding.etSearchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                searchPhrase = p0.toString()
                viewModel.getSearchResult(searchPhrase)
            }
        })
    }
}