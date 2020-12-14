package com.example.appfinalexamen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.appfinalexamen.Entidades.Pastes
import com.example.appfinalexamen.Entidades.PedidoAll
import com.example.appfinalexamen.Entidades.Pedidos
import com.example.appfinalexamen.Modelo.ImageConverter
import com.example.appfinalexamen.Modelo.PastesDB
import com.example.appfinalexamen.R
import kotlinx.android.synthetic.main.item_lista.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListaPedidos.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaPedidos : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view : View = inflater.inflate(R.layout.fragment_lista_pedidos, container, false)
        val activity = activity as Context

        val listaPedidos = view.findViewById<ListView>(R.id.lstPedidos)

        LlenarInformacion(listaPedidos)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListaPedidos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ListaPedidos().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    fun LlenarInformacion(lstPedidos: ListView){
        val datasource = PastesDB(this.requireContext())

        val registros = ArrayList<PedidoAll>()

        val cursor = datasource.consultPedidos()

        while (cursor.moveToNext()){
            val columnas = PedidoAll(cursor.getString(0), cursor.getString(1), cursor.getInt(2))
            registros.add(columnas)
        }

        val adaptador = AdaptadorPersonas(this.requireContext(), registros)
        lstPedidos.adapter = adaptador
    }

    internal class AdaptadorPersonas(context: Context, datos: List<PedidoAll>): ArrayAdapter<PedidoAll>(context, R.layout.item_lista, datos)
    {
        private val imageConverter: ImageConverter = ImageConverter()
        var _datos: List<PedidoAll>
        init {
            _datos = datos
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var inflater = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false)

            val Entidad = getItem(position)

            val lblTitulo = inflater.findViewById<TextView>(R.id.lblTitulo)
            val lblSubTitulo = inflater.findViewById<TextView>(R.id.lblSubTitulo)
            val lblPrecio = inflater.findViewById<TextView>(R.id.lblPrecio)

            lblTitulo.text = Entidad!!._nombreUsuario
            lblSubTitulo.text = Entidad!!._nombrePaste
            lblPrecio.text = Entidad!!._cantidadPastes.toString()

            return inflater
        }
    }
}