package com.example.proyectop.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectop.R
import com.example.proyectop.adapter.AlimentoAdapter
import com.example.proyectop.databinding.FragmentHomeBinding
import com.example.proyectop.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btAddAlimento.setOnClickListener{
            findNavController().navigate(R.id.action_nav_home_to_addAlimentoFragment)
        }
        val alimentoAdapter = AlimentoAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = alimentoAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.obtenerAlimento.observe(viewLifecycleOwner){
                alimentos -> alimentoAdapter.setAlimentos(alimentos)

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}