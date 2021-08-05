package com.mihir.meralist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mihir.meralist.data.Notes
import com.mihir.meralist.data.NotesViewModel
import kotlinx.android.synthetic.main.activity_note_edit_view.*

class NoteEditView : AppCompatActivity() {

    private lateinit var mNotesViewModel: NotesViewModel
    private lateinit var ChangedNote : Notes
    private var idChanged = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit_view)
        supportActionBar?.hide()

        mNotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        val id = intent.extras?.get("note id")
        val text = intent.extras?.get("note text")
        val title = intent.extras?.get("note title")

        ChangedNote = Notes(id as Int,title.toString(),text.toString())

        edTxt_note_edit.setText(ChangedNote.text)
        edTxt_Title_edit.setText(ChangedNote.title)

        //test
        img_deleteNote.setOnClickListener{
            mNotesViewModel.deleteNote(ChangedNote)
            finish()
        }

    }

    override fun onBackPressed() {

        ChangedNote.title = edTxt_Title_edit.text.toString()
        ChangedNote.text = edTxt_note_edit.text.toString()
        mNotesViewModel.updateNote(ChangedNote)
        super.onBackPressed()
    }
}