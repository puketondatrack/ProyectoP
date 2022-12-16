package com.example.proyectop.repository

import androidx.lifecycle.LiveData

import com.example.proyectop.data.AlimentoDao
import com.example.proyectop.model.Alimento


class AlimentoRepository(private val alimentoDao: AlimentoDao) {
    suspend fun guardarAlimento(alimento: Alimento){
        if (alimento.id == 0){
            alimentoDao.agregarAlimento(alimento)
        }
        else{
            alimentoDao.actualizarAlimento(alimento)
        }
    }
    suspend fun eliminarAlimento(alimento: Alimento){
        alimentoDao.eliminarAlimento(alimento)
    }
    val obtenerAlimento: LiveData<List<Alimento>> = alimentoDao.getAlimentos()
}