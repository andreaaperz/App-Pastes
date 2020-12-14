package com.example.appfinalexamen

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.ablanco.imageprovider.ImageProvider
import com.ablanco.imageprovider.ImageSource
import com.example.appfinalexamen.Modelo.ImageConverter
import com.example.appfinalexamen.Modelo.PastesDB
import kotlinx.android.synthetic.main.activity_agregar_paste_nuevo.*

class AgregarPasteNuevo : AppCompatActivity() {
    private lateinit var datasource:PastesDB
    private var img: String? = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_paste_nuevo)

        imageButton.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
                ImageProvider(this@AgregarPasteNuevo).getImage(ImageSource.CAMERA){ bitmap ->
                    img = bitmap?.let { it1 -> ImageConverter().base64(it1) };
                    imageButton.setImageBitmap(bitmap)
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



    fun SaveNewPaste(view: View){
            //TODO se realizara una inserción
        var precio = txtPrecioPaste.text.toString()
            datasource.savePaste(txtNombrePaste.text.toString(), txtDescription.text.toString(),precio.toDouble(), img!!)
            Toast.makeText(
                applicationContext, "Se agregó correctamente",
                Toast.LENGTH_SHORT
            ).show()

        this.finish()
    }

    fun CancelNewPaste(view : View){
        this.finish()
    }
}