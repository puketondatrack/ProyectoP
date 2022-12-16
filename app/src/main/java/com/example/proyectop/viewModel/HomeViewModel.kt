package com.example.proyectop.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.proyectop.data.AlimentoDatabase
import com.example.proyectop.model.Alimento

import com.example.proyectop.repository.AlimentoRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AlimentoRepository
    val obtenerAlimentos: LiveData<List<Alimento>>


    init {

        val alimentoDao = AlimentoDatabase.getDatabase(application).alimentoDao()
        repository = AlimentoRepository(alimentoDao)
        obtenerAlimentos = repository.obtenerAlimento

    }

    fun guardarAlimento(alimento: Alimento) {
        viewModelScope.launch {(repository.guardarAlimento(alimento))}
    }

    fun eliminarAlimento(alimento: Alimento) {
        viewModelScope.launch {(repository.eliminarAlimento(alimento))}

    }
}