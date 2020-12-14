package com.example.appfinalexamen.Controlador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.appfinalexamen.Entidades.Pastes
import com.example.appfinalexamen.Modelo.ImageConverter
import com.example.appfinalexamen.Modelo.PastesDB
import com.example.appfinalexamen.PasteCasa
import com.example.appfinalexamen.R
import kotlinx.android.synthetic.main.fragment_paste_casa.*
import kotlinx.android.synthetic.main.item_lista.view.*
import me.ibrahimsn.lib.SmoothBottomBar


class BottomBarFragmentController : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_bar_fragment_controller)

        //LlenarInformacion()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var navController: NavController = findNavController(R.id.nav_host_fragment)
        val bottomBar: SmoothBottomBar = findViewById(R.id.bottomBar);
        menuInflater.inflate(R.menu.menu_nav,menu)
        bottomBar.setupWithNavController(menu!!,navController)
        return true
    }

}