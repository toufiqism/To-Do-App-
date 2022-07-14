package com.tvl.tvltodolist.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tvl.tvltodolist.R
import com.tvl.tvltodolist.dao.NotesDAO
import com.tvl.tvltodolist.database.NotesDatabase
import com.tvl.tvltodolist.repository.NotesRepository
import com.tvl.tvltodolist.viewmodel.NotesViewModel
import com.tvl.tvltodolist.viewmodel.NotesViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao: NotesDAO = NotesDatabase.getInstance(application).notesDAO
        val repository = NotesRepository(dao)
        val factory = NotesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]
    }
}