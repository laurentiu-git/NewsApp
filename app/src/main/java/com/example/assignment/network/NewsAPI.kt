package com.example.assignment.network

import com.example.assignment.model.NewsResponse
import com.example.assignment.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsAPI {


    @GET("svc/mostpopular/v2/viewed/{days}.json")
    suspend fun getNews(
            @Path("days")
            pageNumber: Int = 1,
            @Query("api-key")
            APIKEY: String = Constants.APIKEY
    ): Response<NewsResponse>
}