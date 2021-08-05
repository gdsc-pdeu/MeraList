package com.mihir.meralist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mihir.meralist.data.Notes
import com.mihir.meralist.data.NotesViewModel
import kotlinx.android.synthetic.main.activity_landing_page.*

class LandingPage : AppCompatActivity() {

    private lateinit var mNotesViewModel: NotesViewModel
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        supportActionBar?.hide()

        mNotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        mNotesViewModel.readAllData.observe(this, { Note->

            if(Note.isNotEmpty()){
                Log.i("TAG", "onCreate: $Note")
                adapter = RecyclerAdapter(Note,imgV_delete)
                recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.adapter = adapter
            }

        })

        imgV_delete.setOnClickListener{
            
            val notesToDelete = adapter.noOfSelected()

            for(i in notesToDelete){
                mNotesViewModel.deleteNote(i)
            }
        }

        Txt_submit.setOnClickListener{
            val noteText = edTxt_notes.text
            edTxt_notes.setText("")

            if(noteText.toString()!= ""){

                val notes= Notes(0,"Title",noteText.toString())
                mNotesViewModel.addNotes(notes)

            }
            else{
                Toast.makeText(this,"Enter something first", Toast.LENGTH_LONG).show()
            }

        }
    }
}