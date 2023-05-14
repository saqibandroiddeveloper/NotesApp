package com.example.notesapp.repositry

import androidx.lifecycle.LiveData
import com.example.notesapp.dao.NotesDao
import com.example.notesapp.model.Notes

class NotesRepositry(val dao: NotesDao) {
    fun getAllNotes():LiveData<List<Notes>> = dao.getNotes()
    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()
    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()
    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}