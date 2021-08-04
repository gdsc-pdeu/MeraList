package com.mihir.meralist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_landing_page.*

class LandingPage : AppCompatActivity() {

    private val listNotes =ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        supportActionBar?.hide()

        Txt_submit.setOnClickListener{
            val noteText = edTxt_notes.text
            edTxt_notes.setText("")
            if(noteText.toString()!= ""){
                listNotes.add(noteText.toString())
                recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.adapter = RecyclerAdapter(listNotes)
            }
            else{
                Toast.makeText(this,"Enter something first", Toast.LENGTH_LONG).show()
            }

        }
    }
}