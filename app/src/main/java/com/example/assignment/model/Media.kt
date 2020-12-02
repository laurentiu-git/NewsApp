package com.example.assignment.model

import com.google.gson.annotations.SerializedName

data class Media(
    var approved_for_syndication: Int,
    var caption: String,
    var copyright: String,
    @SerializedName("media-metadata") var media_metadata: MutableList<MediaMetadata>,
    var subtype: String,
    var type: String
)
