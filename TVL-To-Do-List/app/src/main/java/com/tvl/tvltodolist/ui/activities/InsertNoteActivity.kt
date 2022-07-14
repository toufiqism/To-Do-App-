package com.tvl.tvltodolist.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.tvl.tvltodolist.R
import com.tvl.tvltodolist.dao.NotesDAO
import com.tvl.tvltodolist.database.NotesDatabase
import com.tvl.tvltodolist.databinding.ActivityInsertNoteBinding
import com.tvl.tvltodolist.model.Notes
import com.tvl.tvltodolist.repository.NotesRepository
import com.tvl.tvltodolist.viewmodel.NotesViewModel
import com.tvl.tvltodolist.viewmodel.NotesViewModelFactory
import java.util.*

class InsertNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityInsertNoteBinding
    private lateinit var title: String
    private lateinit var subTitle: String
    private lateinit var notes: String
    private var priority: String = "1"
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_insert_note)
        setContentView(binding.root)
        initialize()
        actions()
    }

    private fun initialize() {
        val dao: NotesDAO = NotesDatabase.getInstance(application).notesDAO
        val repository = NotesRepository(dao)
        val factory = NotesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]
    }

    private fun actions() {
        binding.apply {

            btnDoneNotes.setOnClickListener {
                title = editTextTitle.text.toString()
                subTitle = editTextSubTitle.text.toString()
                notes = editTextNotesData.text.toString()
                createNotes(title, subTitle, notes)
            }
            greenPriority.setOnClickListener {
                priority = "1"
                greenPriority.setImageResource(R.drawable.ic_baseline_done_24)
                yellowPriority.setImageResource(0)
                redPriority.setImageResource(0)
            }
            redPriority.setOnClickListener {
                priority = "2"
                greenPriority.setImageResource(0)
                yellowPriority.setImageResource(0)
                redPriority.setImageResource(R.drawable.ic_baseline_done_24)

            }
            yellowPriority.setOnClickListener {
                priority = "3"
                greenPriority.setImageResource(0)
                yellowPriority.setImageResource(R.drawable.ic_baseline_done_24)
                redPriority.setImageResource(0)
            }

        }
    }

    private fun createNotes(title: String, subTitle: String, notes: String) {

        viewModel.insertNotes(
            Notes(
                id = 0,
                title = title,
                subtitle = subTitle,
                notes = notes,
                notesPriority = priority,
                notesDate = Calendar.getInstance().time.toString()
            )
        )

        Toast.makeText(this@InsertNoteActivity, "Notes Created Successfully", Toast.LENGTH_LONG)
            .show()
        finish()
    }
}