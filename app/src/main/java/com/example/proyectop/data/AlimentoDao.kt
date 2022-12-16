package com.example.proyectop.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyectop.model.Alimento
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase


class AlimentoDao {
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init {
        codigoUsuario = Firebase.auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getAlimentos(): MutableLiveData<List<Alimento>>{
        val listaAlimentos = MutableLiveData<List<Alimento>>()
        firestore
            .collection("Alimentos")
            .document(codigoUsuario)
            .collection("misAlimentos")
            .addSnapshotListener{ snapshot, e ->
                if (e != null){
                    return@addSnapshotListener
                }
                if (snapshot != null){
                    val lista = ArrayList<Alimento>()
                    val alimentos = snapshot.documents
                    alimentos.forEach{
                        val alimento = it.toObject(Alimento::class.java)
                        if (alimento != null){
                            lista.add(alimento)
                        }
                    }
                    listaAlimentos.value = lista
                }
            }
        return listaAlimentos
    }


    fun guardarAlimento(alimento: Alimento) {
        val document: DocumentReference
        if (alimento.id.isEmpty()) {
            //agragar
            document = firestore
                .collection("Alimentos")
                .document(codigoUsuario)
                .collection("misalimentos")
                .document()
            alimento.id = document.id
        }
        else {
            //modifiacar
            document = firestore
                .collection("Alimentos")
                .document(codigoUsuario)
                .collection("misAlimentos")
                .document(alimento.id)
        }
        document.set(alimento)
            .addOnCompleteListener {
                Log.d("guardarAlimento", "guardado con exito")
            }
            .addOnCompleteListener {
                Log.e("guardarAlimento", "Error al guardar ")
            }

    }

    fun eliminarAlimento(alimento: Alimento) {
        if (alimento.id.isNotEmpty()) {
            firestore
                .collection("Alimentos")
                .document(codigoUsuario)
                .collection("misAlimentos")
                .document(alimento.id)
                .delete()
                .addOnCompleteListener {
                    Log.d("eliminarAlimento", "Eliminado con exito")
                }
                .addOnCanceledListener {
                    Log.e("eliminarAlimento", "Eliminado con exito")
                }
        }
    }
}