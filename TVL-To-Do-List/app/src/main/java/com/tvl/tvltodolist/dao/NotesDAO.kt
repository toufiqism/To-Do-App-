package com.tvl.tvltodolist.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    @Query("Select * from Notes_Database")
    fun getAllData(): LiveData<List<Notes>>

    @Insert
    suspend fun insertNotes(notes: Notes): Long

    @Query("Delete from Notes_Database where id=:id")
    suspend fun deleteNotes(id: Int): Int

    @Update
    suspend fun updateNotes(notes: Notes): Int

    @Query("DELETE FROM Notes_Database")
    suspend fun deleteAll(): Int


}