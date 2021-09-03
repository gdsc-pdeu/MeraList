package com.mihir.meralist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mihir.meralist.data.Notes
import kotlinx.android.synthetic.main.item_note.view.*

class RecyclerAdapter(private var notes:List<Notes>, private var imgDel:ImageView):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private lateinit var context:Context
    private lateinit var checkedItems:ArrayList<Notes>
    private var count=0

    inner class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        val title : TextView = item.Txt_Title
        val note : TextView = item.Txt_note

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        context = parent.context
        checkedItems = ArrayList()
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.note.text= notes[position].text
        holder.title.text = notes[position].title

        holder.itemView.setOnClickListener{

            if(holder.itemView.imgV_checked.visibility == View.VISIBLE){
                holder.itemView.imgV_checked.visibility = View.GONE
                checkedItems.remove(notes[position])
                if (checkedItems.size ==0){
                    imgDel.visibility = View.GONE
                }
            }
            else{
                val intent = Intent(context,NoteEditView::class.java)
                intent.putExtra("note id",notes[position].id)
                intent.putExtra("note text",notes[position].text)
                intent.putExtra("note title",notes[position].title)
                context.startActivity(intent)
            }

        }

        holder.itemView.setOnLongClickListener {
            checkedItems.add(notes[position])
            count++
            it.imgV_checked.visibility = View.VISIBLE

            imgDel.visibility = View.VISIBLE
            true
        }

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun notesSelected():ArrayList<Notes>{
        return checkedItems
    }

}