package com.mihir.meralist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.text.toSpannable
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
        // initializing our viewModel
        mNotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        // setting up the recycleView
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        // observing the live data
        mNotesViewModel.readAllData.observe(this, { Note->

            Log.i("TAG", "onCreate: $Note")
            adapter = RecyclerAdapter(Note,imgV_delete)
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.adapter = adapter

        })

        imgV_delete.setOnClickListener{
            //fetching all the notes to be deleted
            val notesToDelete = adapter.notesSelected()

            //deleting notes one by one
            for(i in notesToDelete){
                mNotesViewModel.deleteNote(i)
            }

            // giving a prompt of deleted notes
            Toast.makeText(this,"${notesToDelete.size} notes deleted",Toast.LENGTH_LONG).show()

            // removing the delete button from view
            imgV_delete.visibility = View.GONE
        }

        Txt_submit.setOnClickListener{
            val noteText = edTxt_notes.text
            // giving it a empty string as default heading
            edTxt_notes.setText("")

            if(noteText.toString()!= ""){

                val notes= Notes(0,"Title",noteText.toString().toSpannable())
                mNotesViewModel.addNotes(notes)

            }
            else{
                // if no text is entered and user tries to submit.
                Toast.makeText(this,"Your note is empty", Toast.LENGTH_LONG).show()
            }

        }


    }
}