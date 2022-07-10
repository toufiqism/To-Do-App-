package com.tvl.tvltodolist.repository

import com.tvl.tvltodolist.model.Notes
import com.tvl.tvltodolist.dao.NotesDAO

class NotesRepository(private val notesDao: NotesDAO) {
    val getAllNotes = notesDao.getAllData()

    suspend fun insert(notes: Notes): Long {
        return notesDao.insertNotes(notes)
    }

    suspend fun delete(id: Int): Int {
        return notesDao.deleteNotes(id)
    }

    suspend fun update(notes: Notes): Int {
        return notesDao.updateNotes(notes)
    }

    suspend fun deleteAll() {
        notesDao.deleteAll()
    }
}