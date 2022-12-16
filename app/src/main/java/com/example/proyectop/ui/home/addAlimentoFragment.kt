package com.example.proyectop.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyectop.R
import com.example.proyectop.databinding.FragmentAddAlimentoBinding
import com.example.proyectop.model.Alimento
import com.example.proyectop.viewModel.HomeViewModel


class addAlimentoFragment : Fragment() {
    private var _binding: FragmentAddAlimentoBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentAddAlimentoBinding.inflate(inflater, container, false)

        binding.btAgregar.setOnClickListener {
            agregarAlimento() }

        return binding.root
    }
    private fun agregarAlimento() {
        val nombre = binding.etNombre.text.toString()
        val descripcion = binding.etDescripcion.text.toString()
        val caloria = binding.etCalorias.text.toString()


        if (nombre.isNotEmpty()) {
            val alimento = Alimento(0, nombre, descripcion, caloria)
            homeViewModel.guardarAlimento(alimento)
            Toast.makeText(requireContext(),getText(R.string.ms_AddAlimento), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addAlimentoFragment_to_nav_home)

        }
        else {
            Toast.makeText(requireContext(),getText(R.string.ms_FaltaValores), Toast.LENGTH_LONG).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}