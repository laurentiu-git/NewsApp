package com.example.assignment.db

import android.content.Context
import androidx.room.* //ktlint-disable
import com.example.assignment.model.Result

@Database(
    entities = [Result::class],
    version = 9
)

@TypeConverters(Converters::class)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun getResultDao(): ResultDao

    companion object {
        @Volatile
        private var instance: ResultDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                ResultDatabase::class.java,
                "resultdb.db"
            )
                .build()
    }
}
