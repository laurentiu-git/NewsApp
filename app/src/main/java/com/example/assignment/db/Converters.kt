package com.example.assignment.db

import androidx.room.TypeConverter
import com.example.assignment.model.Media
import com.example.assignment.model.MediaMetadata
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromMedia(media: MutableList<Media>): String {
        return media.elementAt(0).media_metadata.elementAt(0).url
    }

    @TypeConverter
    fun toMedia(url: String): MutableList<Media> {
        return mutableListOf(Media(1,"","",toMediaMetadata(url),"",""))
    }

    @TypeConverter
    fun fromMediaMetadata(metadata: MutableList<MediaMetadata>): String {
        return metadata.elementAt(0).url
    }

    @TypeConverter
    fun toMediaMetadata(url: String): MutableList<MediaMetadata> {
        return mutableListOf(MediaMetadata("",1,url,1))
    }

}