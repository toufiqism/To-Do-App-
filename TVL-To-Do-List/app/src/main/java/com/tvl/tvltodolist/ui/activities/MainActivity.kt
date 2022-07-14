package com.tvl.tvltodolist.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tvl.tvltodolist.DataBinderMapperImpl
import com.tvl.tvltodolist.R
import com.tvl.tvltodolist.dao.NotesDAO
import com.tvl.tvltodolist.database.NotesDatabase
import com.tvl.tvltodolist.databinding.ActivityMainBinding
import com.tvl.tvltodolist.repository.NotesRepository
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
        bind.apply {
            btnAddNotes.setOnClickListener {
                startActivity(Intent(this@MainActivity, InsertNoteActivity::class.java))
            }
        }
        viewModel.allNotes.observe(this,{

        })
    }

    private fun initialization() {
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(bind.root)
        val dao: NotesDAO = NotesDatabase.getInstance(application).notesDAO
        val repository = NotesRepository(dao)
        val factory = NotesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]
    }
}