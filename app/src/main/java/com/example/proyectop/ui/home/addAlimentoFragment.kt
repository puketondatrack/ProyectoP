package com.example.proyectop.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyectop.R
import com.example.proyectop.databinding.FragmentAddAlimentoBinding
import com.example.proyectop.model.Alimentos
import com.example.proyectop.viewModel.HomeViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class addAlimentoFragment : Fragment() {
    private var _binding: FragmentAddAlimentoBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel


    private lateinit var imagenUtiles: ImagenUtiles
    private lateinit var tomarFotoActivity: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentAddAlimentoBinding.inflate(inflater, container, false)

        binding.btAgregar.setOnClickListener {
            binding.progressBar.visibility = ProgressBar.VISIBLE
            binding.msgMensaje.text = getString(R.string.msgSubiendo)
            binding.msgMensaje.visibility = TextView.VISIBLE
            subirAudio()
        }

//Fotos
        tomarFotoActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){result ->
            if (result.resultCode == Activity.RESULT_OK){
                imagenUtiles.actualizaFoto()
            }
        }
        imagenUtiles=ImagenUtiles(requireContext()
            ,binding.btPhoto,binding.btRotaL,binding.btRotaR,binding.imagen,tomarFotoActivity)

        // Inflate the layout for this fragment
        return binding.root
    }


    private fun subirImagen(rutaAudio:String){
        val imagenFile = imagenUtiles.imagenFile
        if (imagenFile.exists() && imagenFile.isFile && imagenFile.canRead()){
            val ruta = Uri .fromFile(imagenFile)
            val rutaNube = "alimentosV/${Firebase.auth.currentUser?.email}/imagenes/${imagenFile.name}"
            val referencia : StorageReference = Firebase.storage.reference.child(rutaNube)
            referencia.putFile(ruta)
                .addOnSuccessListener {
                    referencia.downloadUrl
                        .addOnSuccessListener {
                            val rutaImagen = it.toString()
                            agregarAlimentos(rutaAudio,rutaImagen)
                        }
                }
                .addOnCanceledListener { agregarAlimentos(rutaAudio,"") }
        }
        else{
            agregarAlimentos(rutaAudio,"")
        }
    }
    private fun agregarAlimentos(rutaAudio:String, rutaImagen:String) {
        val nombre = binding.etNombre.text.toString()
        val precio = binding.etprecio.text.toString()


        if (nombre.isNotEmpty()) {
            val alimentos = Alimentos("", nombre, precio, rutaAudio,rutaImagen)
            homeViewModel.gua(alimentos)
            Toast.makeText(requireContext(),getText(R.string.ms_AddAlimento), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addAlimentoFragment_to_nav_home)

        }
        else {
            Toast.makeText(requireContext(),getText(R.string.ms_FaltaValores), Toast.LENGTH_LONG).show()
        }
    }
    private fun eliminarAlimento(rutaAudio:String, rutaImagen:String) {
        val nombre = binding.etNombre.text.toString()
        val precio = binding.etprecio.text.toString()
        )

        if (nombre.isNotEmpty()) {
            val alimentos = Alimentos("", nombre, precio,rutaAudio,rutaImagen)
            homeViewModel.eliminarAlimento(alimentos)
            Toast.makeText(requireContext(),getText(R.string.bt_delete_alimento), Toast.LENGTH_LONG).show()
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