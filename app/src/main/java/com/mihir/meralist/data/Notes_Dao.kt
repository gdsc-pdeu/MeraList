package com.mihir.meralist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Notes_Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note:Notes)

    @Query("SELECT * from notes_table")
    fun readAllNodes():LiveData<List<Notes>>

    @Update
    fun updateNote(note: Notes)

    @Query("Select * from notes_table where id is :sentId")
    fun sendNote(sentId:Int):Notes

    @Delete
    fun deleteNote(note: Notes)
}