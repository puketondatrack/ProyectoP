package com.example.proyectop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectop.databinding.AlimentovistaBinding
import com.example.proyectop.model.Alimento
import com.example.proyectop.ui.home.HomeFragmentDirections


class AlimentoAdapter: RecyclerView.Adapter<AlimentoAdapter.AlimentoViewHolder>() {

    private  var listaAlimentos = emptyList<Alimento>()

    fun setAlimentos(alimentos: List<Alimento>){
        listaAlimentos = alimentos
        notifyDataSetChanged()
    }

    inner class AlimentoViewHolder(private val itemBinding: AlimentovistaBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(alimento: Alimento){
            itemBinding.tvNombre.text = alimento.nombre
            itemBinding.tvDescripcion.text = alimento.decripcion
            itemBinding.tvCalorias.text = alimento.calorias

            itemBinding.vistaAlimentos.setOnClickListener{
                val accion = HomeFragmentDirections.actionNavHomeToUpdateAlimentoFragment(alimento)
                itemView.findNavController().navigate(accion)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimentoViewHolder {
        val itemBinding = AlimentovistaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AlimentoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AlimentoViewHolder, position: Int) {
        val alimento = listaAlimentos[position]
        holder.dibuja(alimento)
    }

    override fun getItemCount(): Int {
        return listaAlimentos.size
    }

}