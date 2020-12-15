package com.example.appfinalexamen

import android.content.Context
import android.content.Intent
import android.database.CursorWindow
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.appfinalexamen.Controlador.DetallePaste
import com.example.appfinalexamen.Entidades.Pastes
import com.example.appfinalexamen.Modelo.ImageConverter
import com.example.appfinalexamen.Modelo.PastesDB
import com.example.appfinalexamen.R
import kotlinx.android.synthetic.main.fragment_paste_casa.*
import kotlinx.android.synthetic.main.item_lista.view.*
import kotlinx.android.synthetic.main.item_paste_lista.view.*
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import java.lang.reflect.Field


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

val listPastesShowcase = mutableListOf<CarouselItem>()

/**
 * A simple [Fragment] subclass.
 * Use the [PasteCasa.newInstance] factory method to
 * create an instance of this fragment.
 */
class PasteCasa : Fragment() {
    // TODO: Rename and change types of parameters
    var listPastesShowcase = mutableListOf<CarouselItem>()
    var registros = ArrayList<Pastes>()

    override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_paste_casa, container, false)
        val activity = activity as Context

        val lstPastes = view.findViewById<ListView>(R.id.lstPastes)
        LlenarInformacion(lstPastes)

        listPastesShowcase = llenarCarousel().toMutableList();
        val carouselPastes = view.findViewById<ImageCarousel>(R.id.carouselPastes)
        carouselPastes.addData(listPastesShowcase)

        lstPastes.setOnItemClickListener { adapterView, view, position, id ->
            var intent = Intent(requireContext(), DetallePaste::class.java).apply {
                putExtra("nombre", registros[position]._NombrePaste)
                putExtra("descripcion", registros[position]._DescripcionPaste)
                putExtra("precio", registros[position]._PrecioPaste)
                putExtra("imagen", registros[position]._img)
            }
            startActivity(intent)
        }


        return view

    }

    fun llenarCarousel(): List<CarouselItem> {
        listPastesShowcase.add(
                CarouselItem(
                        imageUrl = "https://cdn.kiwilimon.com/recetaimagen/37185/th5-640x426-46748.jpg",
                        caption = "¡Paste tradicional!"
                )
        )
        listPastesShowcase.add(
                CarouselItem(
                        imageUrl = "https://mineroingles.com/wp-content/uploads/2019/09/20190901232940_IMG_3481.jpg",
                        caption = "Paste de mole verde"
                )
        )
        listPastesShowcase.add(
                CarouselItem(
                        imageUrl = "https://ceeccafetales.edu.mx/wp-content/uploads/2020/03/f5de33_ec175605853d4332ba66b3853d5e9e67.jpg",
                        caption = "Paste hawaiano"
                )
        )
        return listPastesShowcase
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PasteCasa.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PasteCasa().apply {

                }
    }

    fun LlenarInformacion(lstPastes: ListView) {
        val datasource = PastesDB(this.requireContext())


        val cursor = datasource.consultPastes()

        while (cursor.moveToNext()) {
            val columnas = Pastes(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getString(4))
            registros.add(columnas)
        }

        val adaptador = AdaptadorPersonas(this.requireContext(), registros)
        lstPastes.adapter = adaptador
    }

    internal class AdaptadorPersonas(context: Context, datos: List<Pastes>) : ArrayAdapter<Pastes>(context, R.layout.item_paste_lista, datos) {
        private val imageConverter: ImageConverter = ImageConverter()
        var _datos: List<Pastes>

        init {
            _datos = datos
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var inflater = convertView
                    ?: LayoutInflater.from(context).inflate(R.layout.item_paste_lista, parent, false)

            val Entidad = getItem(position)

            val lblTitulo2 = inflater.findViewById<TextView>(R.id.lblTitulo)
            val lblSubTitulo2 = inflater.findViewById<TextView>(R.id.lblSubTitulo)

            lblTitulo2.text = Entidad!!._NombrePaste
            lblSubTitulo2.text = Entidad!!._DescripcionPaste

            inflater.imgProfile.setImageBitmap(imageConverter.bitmap(Entidad!!._img))

            return inflater
        }
    }

    fun AgregarPastes(view: View) {

    }

    internal class AdaptadorPastes(context: Context, datos: List<Pastes>) : ArrayAdapter<Pastes>(context, R.layout.item_paste_lista, datos) {
        var _datos: List<Pastes>

        init {
            _datos = datos
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = convertView ?: LayoutInflater.from(context).inflate(
                    R.layout.item_paste_lista, parent, false)

            val Entidad = getItem(position)

            inflater.findViewById<ImageView>(R.id.itemLista).setImageBitmap(ImageConverter().bitmap(Entidad!!._img))

            return inflater
        }
    }
}