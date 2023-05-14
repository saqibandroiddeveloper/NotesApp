package com.example.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.database.NotesDatabase
import com.example.notesapp.model.Notes
import com.example.notesapp.repositry.NotesRepositry

class NotesViewModel(application: Application):AndroidViewModel(application) {
    val repositry:NotesRepositry
    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repositry = NotesRepositry(dao)
    }
    fun getAllNotes():LiveData<List<Notes>>{
        return repositry.getAllNotes()
    }
    fun getHighNotes():LiveData<List<Notes>> = repositry.getHighNotes()
    fun getLowNotes():LiveData<List<Notes>> = repositry.getLowNotes()
    fun getMediumNotes():LiveData<List<Notes>> = repositry.getMediumNotes()

    fun insertNotes(notes: Notes){
        repositry.insertNotes(notes)
    }
    fun deleteNotes(id:Int){
        repositry.deleteNotes(id)
    }
    fun  updateNotes(notes: Notes){
        repositry.updateNotes(notes)
    }
}