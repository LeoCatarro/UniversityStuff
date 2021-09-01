package com.example.tomaybedoapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tomaybedoapp.R
import com.example.tomaybedoapp.models.Note
import kotlinx.android.synthetic.main.custom_note_row.view.*

class ListNoteAdapter: RecyclerView.Adapter<ListNoteAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_note_row, parent, false))
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        //holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.id_txt.text = (position+1).toString()
        holder.itemView.noteTitle_txt.text = currentItem.title
        holder.itemView.noteDescription_txt.text = currentItem.description

        holder.itemView.rowNoteLayout.setOnClickListener{
            val action = ListNotesFragmentDirections.actionListNotesToUpdateNotesFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(note: List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }
}