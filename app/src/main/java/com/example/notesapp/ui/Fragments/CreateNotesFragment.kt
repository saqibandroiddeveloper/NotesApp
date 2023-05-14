package com.example.notesapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentCreateNotesBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.viewmodel.NotesViewModel
import java.util.*

class CreateNotesFragment : Fragment() {
    lateinit var binding: FragmentCreateNotesBinding
    var priority:String="1"
val viewModel:NotesViewModel by viewModels ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        binding.apply {
            pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            pGreen.setOnClickListener {
                priority="1"
                pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                pYellow.setImageResource(0)
                pRed.setImageResource(0)
            }
            pRed.setOnClickListener {
                priority = "2"
                pRed.setImageResource(R.drawable.ic_baseline_done_24)
                pYellow.setImageResource(0)
                pGreen.setImageResource(0)
            }
            pYellow.setOnClickListener {
                priority = "3"
                pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                pRed.setImageResource(0)
                pGreen.setImageResource(0)
            }
            saveNotesBtn.setOnClickListener {
                createNotes(it)
            }
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.etTitle.text.toString()
        val subTitle = binding.etSbTitle.text.toString()
        val notesText = binding.etNotes.text.toString()
        val d = Date()
        val noteDates: CharSequence = android.text.format.DateFormat.format("MMMM d, yyyy", d.time)
        val notes = Notes(0,title,subTitle,notesText,noteDates.toString(),priority)
        viewModel.insertNotes(notes)
        Toast.makeText(context, "Notes Created Successfully", Toast.LENGTH_LONG).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }


}