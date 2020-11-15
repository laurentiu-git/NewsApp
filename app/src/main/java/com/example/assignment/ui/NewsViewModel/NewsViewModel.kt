package com.example.assignment.ui.NewsViewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.NewsApplication
import com.example.assignment.model.NewsResponse
import com.example.assignment.model.Result
import com.example.assignment.repository.NewsRepository
import com.example.assignment.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(
        app: Application,
    val newsRepository: NewsRepository
): AndroidViewModel(app) {

    val news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    init {
        getNews()
    }

    fun getNews() = viewModelScope.launch {
        safeNewsCall()
    }

    private fun handleNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveNews(result: Result) = viewModelScope.launch {
        newsRepository.updateAndReplace(result)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteNews(result: Result) = viewModelScope.launch {
        newsRepository.deleteNews(result)
    }

    private suspend fun  safeNewsCall() {
        news.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = newsRepository.getNews()
                news.postValue(handleNewsResponse(response))
            } else {
                news.postValue(Resource.Error("No interrnet connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> news.postValue(Resource.Error("Network failure"))
                else -> news.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun hasInternetConnection(): Boolean{
        val connectivityManager = getApplication<NewsApplication>().getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val activeNetwork = connectivityManager.activeNetwork ?: return false
                val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

                return when {
                    capabilities.hasTransport(TRANSPORT_WIFI) ->true
                    capabilities.hasTransport(TRANSPORT_CELLULAR) ->true
                    capabilities.hasTransport(TRANSPORT_ETHERNET) ->true
                    else -> false
                }
            } else {
                connectivityManager.activeNetworkInfo?.run {
                    return when(type) {
                        TYPE_WIFI -> true
                        TYPE_MOBILE -> true
                        TYPE_ETHERNET -> true
                        else ->false
                    }
                }
            }
        return false

    }

}