package com.example.assignment.db

import androidx.lifecycle.LiveData
import androidx.room.* //ktlint-disable
import com.example.assignment.model.* //ktlint-disable
@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAndReplace(result: Result): Long

    @Query("SELECT * FROM results")
    fun getNews(): LiveData<List<Result>>

    @Delete
    suspend fun deleteResult(result: Result)
}
