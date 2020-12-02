package com.example.assignment.model

data class NewsResponse(
    var copyright: String,
    var num_results: Int,
    var results: MutableList<Result>,
    var status: String
)
