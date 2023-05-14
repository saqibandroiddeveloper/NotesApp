package com.example.notesapp.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.ui.Adaptor.NotesAdaptor
import com.example.notesapp.viewmodel.NotesViewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var adaptor: NotesAdaptor
    val viewModel: NotesViewModel by viewModels()
    var oldNotes = arrayListOf<Notes>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        binding.addNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
            oldNotes = notesList as ArrayList<Notes>
            binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
            adaptor = NotesAdaptor(requireContext(), notesList)
            binding.recyclerView.adapter = adaptor
        }
        binding.apply {
            allNotes.setOnClickListener {
                viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
                    oldNotes = notesList as ArrayList<Notes>
                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                    adaptor = NotesAdaptor(requireContext(), notesList)
                    recyclerView.adapter = adaptor
                }
            }
            filterHighTv.setOnClickListener {
                viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                    oldNotes = notesList as ArrayList<Notes>
                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                    adaptor = NotesAdaptor(requireContext(), notesList)
                    recyclerView.adapter = adaptor
                }
            }
            filterLowTv.setOnClickListener {
                viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                    oldNotes = notesList as ArrayList<Notes>
                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                    adaptor = NotesAdaptor(requireContext(), notesList)
                    recyclerView.adapter = adaptor
                }
            }
            filterMediumTv.setOnClickListener {
                viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                    oldNotes = notesList as ArrayList<Notes>
                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                    adaptor = NotesAdaptor(requireContext(), notesList)
                    recyclerView.adapter = adaptor
                }
            }
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Search Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val newList = oldNotes.filter {
                    it.title.contains(newText!!.trim(),true)
                }
                adaptor = NotesAdaptor(requireContext(), newList)
                binding.recyclerView.adapter = adaptor
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

}