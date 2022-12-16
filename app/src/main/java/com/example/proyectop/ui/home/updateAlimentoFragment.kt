package com.example.proyectop.ui.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyectop.R
import com.example.proyectop.databinding.FragmentUpdateAlimentoBinding
import com.example.proyectop.model.Alimento
import com.example.proyectop.viewModel.HomeViewModel


class updateAlimentoFragment : Fragment() {

    private val args by navArgs<updateAlimentoFragmentArgs>()


    private var _binding: FragmentUpdateAlimentoBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentUpdateAlimentoBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.alimentoArg.nombre)
        binding.etDescripcion.setText(args.alimentoArg.decripcion)
        binding.etCalorias.setText(args.alimentoArg.calorias)


        binding.btUpdateAlimento.setOnClickListener { updateAlimento() }
        binding.btDeleteAlimento.setOnClickListener { deleteAlimento() }
        // Inflate the layout for this fragment
        return binding.root
    }


    private fun updateAlimento() {
        val nombre = binding.etNombre.text.toString()
        val descripcion = binding.etDescripcion.text.toString()
        val calorias = binding.etCalorias.text.toString()


        if (nombre.isNotEmpty()) {
            val alimento =
                Alimento(args.alimentoArg.id, nombre, descripcion, calorias)
            homeViewModel.guardarAlimento(alimento)
            Toast.makeText(
                requireContext(),
                getText(R.string.ms_UpdateAlimento),
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_updateAlimentoFragment_to_nav_home)

        } else {
            Toast.makeText(requireContext(), getString(R.string.ms_FaltaValores), Toast.LENGTH_LONG)
                .show()
        }
    }
        private fun deleteAlimento() {

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(getString(R.string.bt_delete_alimento))
            builder.setMessage(getString(R.string.msg_seguro_borrado) + " ${args.alimentoArg.nombre}?")
            builder.setNegativeButton(getString(R.string.msg_no)) { _, _ -> }
            builder.setPositiveButton(getString(R.string.msg_si)) { _, _ ->
                homeViewModel.eliminarAlimento(args.alimentoArg)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_seguro_borrado),
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updateAlimentoFragment_to_nav_home)
            }

            builder.create().show()
        }
    }