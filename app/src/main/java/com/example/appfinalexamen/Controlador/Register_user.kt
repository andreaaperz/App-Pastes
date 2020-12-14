package com.example.appfinalexamen.Controlador

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.ablanco.imageprovider.ImageProvider
import com.ablanco.imageprovider.ImageSource
import com.example.appfinalexamen.Modelo.ImageConverter
import com.example.appfinalexamen.Modelo.PastesDB
import com.example.appfinalexamen.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register_user.*


class register_user : AppCompatActivity() {
    private lateinit var datasource:PastesDB
    private var img: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        datasource = PastesDB(this)

        var btnImage = findViewById<Button>(R.id.btnImage)
        btnImage.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                ImageProvider(this@register_user).getImage(ImageSource.CAMERA) { bitmap ->
                    img = bitmap?.let { it1 -> ImageConverter().base64(it1) };
                    var imgPreview = findViewById<ImageView>(R.id.imgPreview)
                    imgPreview.setImageBitmap(bitmap)
                }

            } else {
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.CAMERA),
                        42424
                )
            }
        }

    }

    fun SaveUser(view: View){
            //TODO se realizara una modificación
        if(txtNameRegister.text.toString() != "" && txtEmailRegister.text.toString() != "" && txtPasswordRegister.text.toString() != "" && txtEdificioRegister.text.toString() != "") {
            datasource.guardarUsuario(txtNameRegister.text.toString(), txtEmailRegister.text.toString(), txtPasswordRegister.text.toString(), txtEdificioRegister.text.toString(), img!!)
            Toast.makeText(
                    applicationContext, "Se agregó correctamente",
                    Toast.LENGTH_SHORT
            ).show()
            this.finish()
        }
        else{
            Toast.makeText(
                    applicationContext, "Whoops! \n Algún campo esta incompleto, cuidado",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun CancelWindow(view: View){
        this.finish()
    }
}