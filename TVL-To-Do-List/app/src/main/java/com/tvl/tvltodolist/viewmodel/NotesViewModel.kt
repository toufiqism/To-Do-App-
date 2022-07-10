package com.tvl.tvltodolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tvl.tvltodolist.handlers.Event
import com.tvl.tvltodolist.repository.NotesRepository

class NotesViewModel(private val repository: NotesRepository) : ViewModel() {

    val allNotes = repository.getAllNotes
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage
}