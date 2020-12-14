package com.example.appfinalexamen.Controlador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.appfinalexamen.Modelo.ImageConverter
import com.example.appfinalexamen.R
import kotlinx.android.synthetic.main.activity_detalle_paste.*

class DetallePaste : AppCompatActivity() {
    private val imageConverter: ImageConverter = ImageConverter()
    var NombrePaste: String = ""
    var DescripcionPaste: String = ""
    var PrecioPaste: Double = 0.0
    var img: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_paste)

        if(supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        setStatusBarTranslucent(true)

        if(intent.extras != null){
            NombrePaste = intent.extras!!.getString("nombre")!!
            DescripcionPaste = intent.extras!!.getString("descripcion")!!
            PrecioPaste = intent.extras!!.getDouble("precio")
            img = intent.extras!!.getString("imagen")!!
        }


        app_bar_image.setImageBitmap(imageConverter.bitmap(img))
        collapsingToolbar.title = NombrePaste
        textDescripcion.text = DescripcionPaste
        txtPrice.text = "$$PrecioPaste c/u"
        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        )
    }

    private fun setStatusBarTranslucent(makeTranslucent:Boolean){
        if(makeTranslucent){
            window.statusBarColor = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        }else{
            window.statusBarColor = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        }
    }
}