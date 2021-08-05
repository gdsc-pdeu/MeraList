package com.mihir.meralist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class],version = 1,exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun notesDao():Notes_Dao

    companion object{
        @Volatile
        private var INSTANCE: NotesDatabase?=null

        public fun getDatabase(context: Context):NotesDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}