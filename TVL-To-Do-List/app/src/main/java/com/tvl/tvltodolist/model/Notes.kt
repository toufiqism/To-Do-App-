package com.tvl.tvltodolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes_Database")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "Notes_title")
    var title: String,
    @ColumnInfo(name = "Notes_notes")
    var notes: String,
    @ColumnInfo(name = "Notes_subtitle")
    var subtitle: String,
    @ColumnInfo(name = "Notes_notesdate")
    var notesDate: String,
    @ColumnInfo(name = "Notes_notespriority")
    var notesPriority: String,
)
