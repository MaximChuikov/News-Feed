package com.example.news_feed.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_feed.models.UsersModel
import com.example.news_feed.repository.ApiNetworkResponse
import com.example.news_feed.repository.INetworkRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersFragmentViewModel : ViewModel() {
    val liveData = MutableLiveData<List<UsersModel>>()
    private val network: INetworkRequest = ApiNetworkResponse()
    fun init() {
        viewModelScope.launch{
            val response = withContext(Dispatchers.IO) {
                network.sendData()
            }
            if (response.isSuccessful) {
                val responses = response.body()
                val news = responses?.let { UsersModel.getAllNews(it) }
                liveData.postValue(news)
            }
        }
    }
}