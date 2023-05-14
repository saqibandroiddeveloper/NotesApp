package com.example.notesapp.ui.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemNotesBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.ui.Fragments.HomeFragmentDirections

class NotesAdaptor(val context: Context, val notesList: List<Notes>) : Adapter<NotesAdaptor.NotesViewHolding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolding {
        val view = ItemNotesBinding.inflate(LayoutInflater.from(context), parent, false)
        return NotesViewHolding(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolding, position: Int) {
        val data = notesList[position]
        holder.title.text = data.title
        holder.notesDesc.text = data.sbTitle
        holder.notesDate.text = data.date
        when(data.priority){
            "1"->{
                holder.notesPriority.setBackgroundResource(R.drawable.green_circle)
            }
            "2"->{
                holder.notesPriority.setBackgroundResource(R.drawable.red_circle)
            }
            "3"->{
                holder.notesPriority.setBackgroundResource(R.drawable.yellow_circle)
            }
        }
       holder.itemView.setOnClickListener{
         val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
           Navigation.findNavController(it).navigate(action)
       }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class NotesViewHolding(private val binding: ItemNotesBinding) : ViewHolder(binding.root) {
        val title = binding.notesTitle
        val notesDesc = binding.notesST
        val notesDate = binding.notesDate
        val notesPriority = binding.notesPriority
    }

}