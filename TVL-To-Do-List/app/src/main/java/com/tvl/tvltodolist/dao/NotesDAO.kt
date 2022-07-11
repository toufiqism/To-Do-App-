package com.tvl.tvltodolist.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tvl.tvltodolist.model.Notes

@Dao
interface NotesDAO {
    @Query("Select * from Notes_Database")
    fun getAllData(): LiveData<List<Notes>>

    @Insert
    suspend fun insertNotes(notes: Notes): Long

    @Delete
    suspend fun deleteNotes(notes: Notes): Int

    @Update
    suspend fun updateNotes(notes: Notes): Int

    @Query("DELETE FROM Notes_Database")
    fun deleteAll(): Int


}