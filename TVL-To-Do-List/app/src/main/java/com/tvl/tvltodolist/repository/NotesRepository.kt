package com.tvl.tvltodolist.model

class NotesRepository(private val notesDao: NotesDAO) {
    val getAllData = notesDao.getAllData()

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