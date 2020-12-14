package com.example.appfinalexamen.Modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class PastesDB(context: Context) {
    private val openHelper: DbOpenHelper = DbOpenHelper(context)
    private val database: SQLiteDatabase

    init {
        database = openHelper.writableDatabase
    }

    //TODO Seccion de la base de datos para consultar, borrar y guardar nuevos pastes
    fun consultPastes() : Cursor{
        return database.rawQuery("select * from tblPastes".trimIndent(),null)
    }

    fun consultPasteByName(NombrePaste : String) : Cursor{
        return database.rawQuery("select * from tblPastes where NombrePaste like %$NombrePaste%".trimIndent(),null)
    }

    fun savePaste(NombrePaste: String, Descripcion: String, Precio: Double, img: String){
        val values = ContentValues()
        values.put("NombrePaste",NombrePaste)
        values.put("DescripcionPaste",Descripcion)
        values.put("PrecioPaste", Precio)
        values.put("img",img)

        database.insert("tblPastes",null, values)
        database.close()
    }

    fun modifyPaste(IdPaste: Int, NombrePaste: String, Descripcion: String, Precio: Double, img: String){
        val values = ContentValues()
        values.put("NombrePaste",NombrePaste)
        values.put("DescripcionPaste",Descripcion)
        values.put("PrecioPaste", Precio)
        values.put("img",img)
        val a = arrayOf(IdPaste.toString())
        database.update("tblPastes", values, "IdPaste=?", a)
        database.close()
    }




    //TODO Seccion de la base de datos para consultar, y guardar nuevos usuarios
    fun guardarUsuario(_NombreUsuario:String, _CorreoElectronico:String, _Password: String, _Edificio: String, _Image: String) {
        val values = ContentValues()
        values.put("NombreUsuario", _NombreUsuario)
        values.put("CorreoElectronico", _CorreoElectronico)
        values.put("Password", _Password)
        values.put("Edificio", _Edificio)
        values.put("Image", _Image)
        database.insert("tblUsuarios", null, values)
    }

    fun consultarLogin(_CorreoElectronico: String, _Password: String):Cursor {
        return  database.rawQuery(
            """
            select idUsuario 
            from tblUsuarios 
            where (CorreoElectronico = '$_CorreoElectronico' AND Password = '$_Password')
        """.trimIndent(), null
        )
    }


    //TODO Seccion de la base de datos para consultar, borrar, modificar y guardar pedidos
    fun consultPedidos() : Cursor{
        return database.rawQuery("""select Usu.NombreUsuario, Past.NombrePaste, Ped.cantidadPastes 
                        from tblPedidos as Ped 
    INNER JOIN tblPastes as Past
 on Ped.idPastePedido = Past.idPaste
INNER JOIN tblUsuarios as Usu
 on Ped.idUsuarioCliente = Usu.idUsuario""".trimIndent(),null)
    }

    fun consultPedidos(idPedido : Int) : Cursor{
        return database.rawQuery("" +
                ("""SELECT tblUsuarios.*, tblPastes.idPastePedido, tblPedidos.cantidadPastes 
                            FROM tblUsuarios
                            INNER JOIN tblPastes ON tblPedidos.cantidadPastes = tblPastes.idPastePedido
                               INNER JOIN tblPedidos ON tblPastes.idPastePedido = tblPedidos.cantidadPastes""").trimIndent(),null)
    }

    fun createPedido(_idPastePedido: Int ,_cantidadPastes: Int,_idUsuarioCliente : Int){
        val values = ContentValues()
        values.put("idPastePedido",_idPastePedido )
        values.put("cantidadPastes",_cantidadPastes)
        values.put("idUsuarioCliente",_idUsuarioCliente)
        values.put("isCompleted",false)

        database.insert("tblPedidos",null, values)
        database.close()
    }

    fun modifyPedido(_idPedido: Int, _idPastePedido: Int, _cantidadPastes: Int, _idUsuarioCliente: Int, _isCompleted: Boolean){
        val values = ContentValues()
        values.put("idPastePedido",_idPastePedido)
        values.put("cantidadPastes",_cantidadPastes)
        values.put("idUsuarioCliente",_idUsuarioCliente)
        values.put("isCompleted",_isCompleted)
        val a = arrayOf(_idPedido.toString())
        database.update("tblPedidos", values, "idPedidos=?", a)
        database.close()
    }
}