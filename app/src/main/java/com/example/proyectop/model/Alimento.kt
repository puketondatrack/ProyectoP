package com.example.proyectop.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Alimento(

    var id:String,
    val nombre: String,
    val decripcion: String?,
    val calorias: String?,
    val rutaAudio: String?,
    val rutaImagen: String?
):Parcelable{
    constructor():
            this("","","","","","")
}