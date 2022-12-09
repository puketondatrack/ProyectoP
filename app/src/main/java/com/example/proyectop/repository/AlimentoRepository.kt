package com.example.proyectop.repository

import androidx.lifecycle.MutableLiveData
import com.example.proyectop.data.AlimentoDao
import com.example.proyectop.model.Alimentos

class AlimentoRepository(private val alimentoDao: AlimentoDao) {

    fun guardarAlimento(alimentos: Alimentos){
        alimentoDao.guardarAlimento(alimentos)
    }
    fun eliminarAlimento(alimentos: Alimentos){
        alimentoDao.eliminarAlimento(alimentos)
    }
val obtenerAlimento: MutableLiveData<List<Alimentos>> = alimentoDao.getalimento()

}