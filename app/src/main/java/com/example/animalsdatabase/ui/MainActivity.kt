package com.example.animalsdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.animalsdatabase.R

class MainActivity : AppCompatActivity() {

    //Variables
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host)
        window.statusBarColor = R.drawable.toolbar_gradient
    }
}