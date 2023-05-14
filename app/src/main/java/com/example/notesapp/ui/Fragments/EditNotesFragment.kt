package com.example.notesapp.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentEditNotesBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotesFragment : Fragment() {
    lateinit var binding: FragmentEditNotesBinding
    val notes by navArgs<EditNotesFragmentArgs>()
    val viewModel: NotesViewModel by viewModels()
    var priority: String = "1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        binding.apply {
            etTitle.setText(notes.data.title)
            etSbTitle.setText(notes.data.sbTitle)
            etNotes.setText(notes.data.notes)
            when (notes.data.priority) {
                "1" -> {
                    priority = "1"
                    pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                    pYellow.setImageResource(0)
                    pRed.setImageResource(0)
                }
                "2" -> {
                    priority = "2"
                    pRed.setImageResource(R.drawable.ic_baseline_done_24)
                    pYellow.setImageResource(0)
                    pGreen.setImageResource(0)
                }
                "3" -> {
                    priority = "3"
                    pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                    pRed.setImageResource(0)
                    pGreen.setImageResource(0)
                }
            }
            pGreen.setOnClickListener {
                priority = "1"
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

            editNotesBtn.setOnClickListener {
                editNotes(it)
            }
        }
        return binding.root
    }

    private fun editNotes(it: View?) {
        val id = notes.data.id
        val title = binding.etTitle.text.toString()
        val subTitle = binding.etSbTitle.text.toString()
        val notesText = binding.etNotes.text.toString()
        val d = Date()
        val noteDates: CharSequence = android.text.format.DateFormat.format("MMMM d, yyyy", d.time)
        val notes = Notes(id, title, subTitle, notesText, noteDates.toString(), priority)
        viewModel.updateNotes(notes)
        Toast.makeText(context, "Notes Updated Successfully", Toast.LENGTH_LONG).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu_notes) {
            val buttomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
            buttomSheet.setContentView(R.layout.dialog_delete)
            val textViewYes = buttomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo = buttomSheet.findViewById<TextView>(R.id.dialog_no)
            textViewNo?.setOnClickListener {
                buttomSheet.dismiss()
            }
            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(notes.data.id)
                Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)

            }
            buttomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}


