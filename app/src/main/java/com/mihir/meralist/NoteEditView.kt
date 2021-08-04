package com.mihir.meralist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_note_edit_view.*

class NoteEditView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit_view)
        supportActionBar?.hide()

        val text = intent.extras?.get("note data")
        edTxt_note_edit.setText(text.toString())

    }
}