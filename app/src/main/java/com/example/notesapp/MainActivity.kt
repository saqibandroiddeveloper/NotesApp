package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    lateinit var naveController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        naveController = findNavController(R.id.fragmentContainerView)
        setupActionBarWithNavController(naveController)
    }

    override fun onNavigateUp(): Boolean {
        return naveController.navigateUp() || super.onNavigateUp()
    }
}