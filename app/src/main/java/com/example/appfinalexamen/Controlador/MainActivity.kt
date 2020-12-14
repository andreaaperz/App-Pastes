package com.example.appfinalexamen.Controlador

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appfinalexamen.Modelo.PastesDB
import com.example.appfinalexamen.R
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

private lateinit var datasource:PastesDB
var user = "";
var contra = "";

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datasource = PastesDB(this)
        user = txtCorreo.text.toString()
        contra = txtPassword.text.toString()


        btnEntrar.setOnClickListener {
            try{
            Handler().postDelayed(Runnable {

                val cursor = datasource.consultarLogin(txtCorreo.text.toString(), txtPassword.text.toString())
                   // var cursor = true
                    //if(cursor){

                if (cursor.moveToFirst()) {
                    var intent = Intent(this, BottomBarFragmentController::class.java)
                    intent.putExtra("EXTRA_SESSION_ID", cursor.moveToFirst());
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Credenciales incorrectas",Toast.LENGTH_SHORT).show()
                }
            }, 500)
            } catch (e:Exception){
                print(e)
            }
        }

        btnRegistro.setOnClickListener{
            var intent = Intent(this, register_user::class.java)
            startActivity(intent)
        }
    }
}