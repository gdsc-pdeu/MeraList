package com.mihir.meralist

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.Log
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.lifecycle.ViewModelProvider
import com.mihir.meralist.data.Notes
import com.mihir.meralist.data.NotesViewModel
import com.nvt.color.ColorPickerDialog
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

        ChangedNote = Notes(id as Int,title.toString(),text.toString().toSpannable())

        edTxt_note_edit.setText(ChangedNote.text)
        edTxt_Title_edit.setText(ChangedNote.title)

        img_deleteNote.setOnClickListener{
            mNotesViewModel.deleteNote(ChangedNote)
            finish()
        }
        imgV_bold.setOnClickListener{
            Log.i("TAG", "onCreate: reached here")
            val editableString:Spannable = SpannableStringBuilder(edTxt_note_edit.text)
            editableString.setSpan( StyleSpan(Typeface.BOLD ), edTxt_note_edit.selectionStart, edTxt_note_edit.selectionEnd,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            edTxt_note_edit.setText(editableString)
            Log.d("TAG", "onCreate: $editableString")
        }
        imgV_italic.setOnClickListener{
            Log.i("TAG", "onCreate: reached here")
            val editableString:Spannable = SpannableStringBuilder(edTxt_note_edit.text)
            editableString.setSpan( StyleSpan(Typeface.ITALIC), edTxt_note_edit.selectionStart, edTxt_note_edit.selectionEnd,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            edTxt_note_edit.setText(editableString)
            Log.d("TAG", "onCreate: $editableString")
        }

        homeBtn.setOnClickListener{
            val intent = Intent(this, LandingPage::class.java)
            // start your next activity
            startActivity(intent)
            Toast.makeText(applicationContext,"Changes Discarded!",Toast.LENGTH_SHORT).show()

        }

        imgV_color.setOnClickListener{
            val colorPicker = ColorPickerDialog(
                this,
                Color.BLACK, // color init
                true, // true is show alpha
                object : ColorPickerDialog.OnColorPickerListener {
                    override fun onCancel(dialog: ColorPickerDialog?) {
                    }
                    override fun onOk(dialog: ColorPickerDialog?, colorPicker: Int) {
                        edTxt_note_edit.setTextColor(colorPicker)
                    }
                })
            colorPicker.show()
        }
    }

    override fun onBackPressed() {

        ChangedNote.title = edTxt_Title_edit.text.toString()
        ChangedNote.text = edTxt_note_edit.text
        mNotesViewModel.updateNote(ChangedNote)
        super.onBackPressed()
    }
}