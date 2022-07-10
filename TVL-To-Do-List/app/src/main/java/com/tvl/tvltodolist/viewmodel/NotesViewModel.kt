package com.tvl.tvltodolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tvl.tvltodolist.handlers.Event
import com.tvl.tvltodolist.model.Notes
import com.tvl.tvltodolist.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(private val repository: NotesRepository) : ViewModel() {

    val allNotes = repository.getAllNotes
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage


    fun insertNotes(notes: Notes) = viewModelScope.launch {
        val noOfRows = repository.insert(notes)
        if (noOfRows > 1) {
            statusMessage.value = Event("Added Successfully ")
        } else {
            statusMessage.value = Event("Error Occurred! ")
        }
    }

    fun updateNotes(notes: Notes) = viewModelScope.launch {
        val noOfRows = repository.update(notes)
        if (noOfRows > 1) {
            statusMessage.value = Event("Added Successfully ")
        } else {
            statusMessage.value = Event("Error Occurred! ")
        }
    }

    fun deleteNotes(id: Int) = viewModelScope.launch {
        val noOfRows = repository.delete(id)
        if (noOfRows > 1) {
            statusMessage.value = Event("Added Successfully ")
        } else {
            statusMessage.value = Event("Error Occurred! ")
        }
    }
}