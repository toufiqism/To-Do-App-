package com.tvl.tvltodolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tvl.tvltodolist.model.Notes
import com.tvl.tvltodolist.dao.NotesDAO


@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract val notesDAO: NotesDAO

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null
        fun getInstance(context: Context): NotesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "Notes_Database"
                    ).build()
                }
                return instance
            }

        }
    }
}