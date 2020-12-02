package com.example.assignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "results"
)
data class Result(
    @PrimaryKey(autoGenerate = true) var primaryKey: Int? = null,
    @SerializedName("abstract") var desciption: String,
    var adx_keywords: String,
    var asset_id: Long,
    var byline: String,
    var eta_id: Int,
    var media: MutableList<Media>,
    var nytdsection: String,
    var published_date: String,
    var section: String,
    var source: String,
    var subsection: String,
    var title: String,
    var type: String,
    var updated: String,
    var uri: String,
    var url: String
) : Serializable
