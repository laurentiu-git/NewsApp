package com.example.assignment.model

data class NewsResponse(
    var copyright: String,
    var num_results: Int,
    var results: List<Result>,
    var status: String
)