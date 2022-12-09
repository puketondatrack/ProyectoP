package com.example.proyectop.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectop.data.AlimentoDao
import com.example.proyectop.model.Alimentos
import com.example.proyectop.repository.AlimentoRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: AlimentoRepository = AlimentoRepository(AlimentoDao())
    val obtenerAlimento: MutableLiveData<List<Alimentos>>


    init {

        obtenerAlimento = repository.obtenerAlimento

    }

    fun guardarAlimento(alimentos: Alimentos) {
        repository.guardarAlimento(alimentos)
    }

    fun eliminarAlimento(alimentos: Alimentos) {
        repository.eliminarAlimento(alimentos)

    }
}