package com.tvl.tvltodolist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tvl.tvltodolist.R
import com.tvl.tvltodolist.model.Notes

class NotesAdapter : RecyclerView.Adapter<NotesAdapterViewHolder>() {

    var notesList = ArrayList<Notes>()

    fun setList(notes: List<Notes>) {
        notesList.clear()
        notesList.addAll(notes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_notes, parent, false)
        return NotesAdapterViewHolder(listItem)

    }

    override fun onBindViewHolder(holder: NotesAdapterViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}

class NotesAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(note: Notes) {

    }
}

