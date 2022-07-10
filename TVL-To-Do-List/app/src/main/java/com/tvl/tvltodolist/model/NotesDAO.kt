package com.tvl.tvltodolist.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {

    @Query("DELETE FROM Notes_Database")
    suspend fun deleteAll(): Int

    @Query("Select * from Notes_Database")
    fun getAllData(): LiveData<List<Notes>>

    @Insert
    fun insertNotes(notes: Notes)

    @Query("Delete from Notes_Database where id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)


}