package com.mihir.meralist.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class NotesViewModel(application: Application):AndroidViewModel(application) {

    val readAllData: LiveData<List<Notes>>
    //val noteEdit: Notes
    val repository:NotesRepo

    init {
        val dao = NotesDatabase.getDatabase(application).notesDao()
        repository = NotesRepo(dao)
        readAllData = repository.readAllData
        //noteEdit = repository.selectedNote

    }

    fun addNotes(note:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }

    }

    fun updateNote(note: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun sendNote(sentId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendNote(sentId)
        }

    }

    fun deleteNote(note: Notes){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteNote(note)
        }
    }
}