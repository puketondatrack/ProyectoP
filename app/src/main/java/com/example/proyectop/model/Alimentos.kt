package com.example.proyectop.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Alimentos(

var id: String,
val nombre: String,
val precio: String?,
val rutaImagen: String?,
val descriocion: String?

): Parcelable{
    constructor():
    this( "" ,"","","","")
}