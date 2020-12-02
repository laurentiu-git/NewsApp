package com.example.assignment.repository

import com.example.assignment.db.ResultDatabase
import com.example.assignment.model.Result
import com.example.assignment.network.RetrofitInstance

class NewsRepository(
    val db: ResultDatabase
) {
    suspend fun getNews(pageNumber: Int) = RetrofitInstance.api.getNews(pageNumber)

    suspend fun updateAndReplace(result: Result) = db.getResultDao().updateAndReplace(result)

    fun getSavedNews() = db.getResultDao().getNews()

    suspend fun deleteNews(result: Result) = db.getResultDao().deleteResult(result)
}
