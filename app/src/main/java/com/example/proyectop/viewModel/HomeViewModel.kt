package com.example.proyectop.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.proyectop.data.AlimentoDao
import com.example.proyectop.model.Alimento
import com.example.proyectop.repository.AlimentoRepository


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AlimentoRepository = AlimentoRepository(AlimentoDao())
    val obtenerAlimento: MutableLiveData<List<Alimento>>


    init {
        obtenerAlimento = repository.obtenerAlimento
    }

    fun guardarAlimento(alimento: Alimento) {
       repository.guardarAlimento(alimento)
    }

    fun eliminarAlimento(alimento: Alimento) {
        repository.eliminarAlimento(alimento)

    }
}