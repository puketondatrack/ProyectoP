package com.example.proyectop.repository

import androidx.lifecycle.MutableLiveData
import com.example.proyectop.data.AlimentoDao
import com.example.proyectop.model.Alimento


class AlimentoRepository(private val alimentoDao: AlimentoDao) {
     fun guardarAlimento(alimento: Alimento){
         alimentoDao.guardarAlimento(alimento)
     }

     fun eliminarAlimento(alimento: Alimento){
        alimentoDao.eliminarAlimento(alimento)
    }
    val obtenerAlimento: MutableLiveData<List<Alimento>> = alimentoDao.getAlimentos()
}