package com.example.proyectop.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "alimento")
data class Alimento(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="nombre")
    val nombre: String,
    @ColumnInfo(name="descripcion")
    val decripcion: String?,
    @ColumnInfo(name="calorias")
    val calorias: String?,
    @ColumnInfo(name="rutaAudio")
    val rutaAudio: String?,
    @ColumnInfo(name="rutaImagen")
    val rutaImagen: String?,
):Parcelable