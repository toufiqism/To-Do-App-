package com.tvl.tvltodolist.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tvl.tvltodolist.R
import com.tvl.tvltodolist.dao.NotesDAO
import com.tvl.tvltodolist.database.NotesDatabase
import com.tvl.tvltodolist.databinding.ActivityMainBinding
import com.tvl.tvltodolist.model.Notes
import com.tvl.tvltodolist.repository.NotesRepository
import com.tvl.tvltodolist.ui.adapters.NotesAdapter
import com.tvl.tvltodolist.viewmodel.NotesViewModel
import com.tvl.tvltodolist.viewmodel.NotesViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialization()
        actions()
    }

    private fun actions() {
        viewModel.allNotes.observe(this, Observer { notes ->
            adapter.setList(notes = notes)
            bind.rclViewNotes.layoutManager = GridLayoutManager(this, 2)
            bind.rclViewNotes.adapter = adapter
            adapter.notifyDataSetChanged()
        })
        bind.btnAddNotes.setOnClickListener {
            startActivity(Intent(this, InsertNoteActivity::class.java))
        }
        viewModel.message.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    it,
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
            }
        })

    }

    lateinit var adapter: NotesAdapter
    private fun initialization() {
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(bind.root)
        val dao: NotesDAO = NotesDatabase.getInstance(application).notesDAO
        val repository = NotesRepository(dao)
        val factory = NotesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]
        adapter = NotesAdapter()
    }
}