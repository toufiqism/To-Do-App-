package com.tvl.tvltodolist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tvl.tvltodolist.R
import com.tvl.tvltodolist.model.Notes

class NotesAdapter() : RecyclerView.Adapter<NotesAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_notes, parent, false)
        return NotesAdapterViewHolder(listItem)

    }

    override fun onBindViewHolder(holder: NotesAdapterViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    var notesList = ArrayList<Notes>()

    fun setList(notes: List<Notes>) {
        notesList.clear()
        notesList.addAll(notes)
    }
}

class NotesAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var notesPriority: View
    lateinit var notesTitle: TextView
    lateinit var notesSubTitle: TextView
    lateinit var notesData: TextView
    lateinit var notesDate: TextView
    fun bind(note: Notes) {

        notesTitle = itemView.findViewById(R.id.notesTitle)
        notesSubTitle = itemView.findViewById(R.id.notesSubTitle)
        notesData = itemView.findViewById(R.id.notesData)
        notesDate = itemView.findViewById(R.id.notesDate)
        notesPriority = itemView.findViewById(R.id.notesPriority)

        notesTitle.text = note.title
        notesSubTitle.text = note.subtitle
        notesData.text = note.notes
        notesDate.text = note.notesDate
        if (note.notesPriority.contains("1")) {
            notesPriority.setBackgroundResource(R.drawable.green_shape)
        }
        if (note.notesPriority.contains("2")) {
            notesPriority.setBackgroundResource(R.drawable.yellow_shape)
        }
        if (note.notesPriority.contains("3")) {
            notesPriority.setBackgroundResource(R.drawable.red_shape)
        }
    }
}

