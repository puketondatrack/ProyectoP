package com.example.proyectop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectop.model.Alimentos

class AlimentoAdapter: RecyclerView.Adapter<AlimentoAdapter.AlimentoViewHolder>() {

    private var listaAlimentos = emptyList<Alimentos>()


    fun setAlimento(alimentos: List<Alimentos>){
        listaAlimentos = alimentos
        notifyDataSetChanged()
    }


    inner class AlimentoViewHolder(private val itemBinding: AlimentosFilaBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(alimentos: Alimentos){
            itemBinding.tvNombre.text = alimentos.nombre
            itemBinding.tvPrecio.text = alimentos.precio



            if (alimentos.rutaImagen?.isNotEmpty()==true){
                Glide.with(itemBinding.root.context)
                    .load(alimentos.rutaImagen)
                    .into(itemBinding.imagen)
            }

            itemBinding.vistaFila.setOnClickListener{
                val accion = HomeFragmentDirections.actionNavHomeToUpdateLugarFragment(alimentos)
                itemView.findNavController().navigate(accion)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimentoViewHolder {
        val itemBinding = AlimentosFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AlimentoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AlimentoViewHolder, position: Int) {
        val alimentos = listaAlimentos[position]
        holder.dibuja(alimentos)
    }

    override fun getItemCount(): Int {
        return listaAlimentos.size
    }


}