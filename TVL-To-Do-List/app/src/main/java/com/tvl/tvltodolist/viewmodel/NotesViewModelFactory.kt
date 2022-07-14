package com.tvl.tvltodolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tvl.tvltodolist.repository.NotesRepository

class NotesViewModelFactory(private val repo: NotesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(repo) as T
        }
        throw  IllegalArgumentException("Unknown View Models")
    }
}

