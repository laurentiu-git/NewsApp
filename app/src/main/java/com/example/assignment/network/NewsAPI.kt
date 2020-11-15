package com.example.assignment.network

import com.example.assignment.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsAPI {

    @GET("svc/mostpopular/v2/viewed/1.json?api-key=FcsZlqmOCpc3Nw9RuOAmfqwZVSsVoWOl")

    suspend fun getNews(): Response<NewsResponse>
}