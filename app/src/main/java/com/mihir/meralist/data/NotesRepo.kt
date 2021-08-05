package com.mihir.meralist.data

import androidx.lifecycle.LiveData

class NotesRepo(private val dao:Notes_Dao) {

    val readAllData: LiveData<List<Notes>> = dao.readAllNodes()
    //lateinit var selectedNote: Notes

    fun addNote(note: Notes){

        dao.addNote(note)
    }
    fun updateNote(note: Notes){
        dao.updateNote(note)
    }
    fun sendNote(sentId:Int){
        //selectedNote = dao.sendNote(sentId)
    }
    fun deleteNote(note: Notes){
        dao.deleteNote(note)
    }
}