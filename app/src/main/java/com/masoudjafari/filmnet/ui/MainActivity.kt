package com.masoudjafari.filmnet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.masoudjafari.filmnet.R
import com.masoudjafari.filmnet.data.Repository
import com.masoudjafari.filmnet.data.model.VideosItem
import com.masoudjafari.filmnet.data.remote.RetrofitService
import com.masoudjafari.filmnet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val searchAdapter = SearchAdapter()

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
            searchAdapter.setData(viewModel.searchResponse.value?.data?.videos as List<VideosItem>)
        }
    }

    private fun setTextChangeListener() {
        binding.etSearchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                viewModel.getSearchResponse(p0.toString())
            }
        })
    }
}