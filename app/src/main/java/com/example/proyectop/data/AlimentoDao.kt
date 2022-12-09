package com.example.proyectop.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.proyectop.model.Alimentos
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class AlimentoDao {
    private  var codigoUsuario:String
    private var firetore: FirebaseFirestore

    init {
        codigoUsuario = Firebase.auth.currentUser?.email.toString()
        firetore = FirebaseFirestore.getInstance()
        firetore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }


    fun getalimento(): MutableLiveData<List<Alimentos>> {
        val listaAlimentos = MutableLiveData<List<Alimentos>>()
        firetore
            .collection("Alimentos")
            .document(codigoUsuario)
            .collection("misalimentos")
            .addSnapshotListener{snapshot,e ->
                if (e!= null){
                    return@addSnapshotListener

                }
                if (snapshot != null){
                    val lista = ArrayList<Alimentos>()
                    val alimento = snapshot.documents
                    alimento.forEach{
                        val alimentos = it .toObject(Alimentos::class.java)
                        if (alimentos != null){
                            lista.add(alimentos)
                        }
                    }
                    listaAlimentos.value = lista
                }

            }
        return listaAlimentos


    }
    fun guardarAlimento(alimentos: Alimentos) {
        val document: DocumentReference
        if (alimentos.id.isEmpty()) {
            document = firetore
                .collection("Alimentos")
                .document(codigoUsuario)
                .collection("misalimentos")
                .document()
            alimentos.id = document.id

        } else {
            document = firetore
                .collection("Alimentos")
                .document(codigoUsuario)
                .collection("misalimentos")
                .document(alimentos.id)
        }
        document.set(alimentos)
            .addOnCompleteListener {
                Log.d("guardarAlimento", "guardado con exito")
            }
            .addOnCompleteListener {
                Log.e("guardarAlimento", "Error al guardar ")
            }
    }

    fun eliminarAlimento(alimentos: Alimentos) {
        if (alimentos.id.isEmpty()) {
            firetore
                .collection("Alimentos")
                .document(codigoUsuario)
                .collection("misalimentos")
                .document(alimentos.id)
                .delete()
                .addOnCompleteListener{
                    Log.d("eliminarAlimento", "Eliminado con exito")
                }
                .addOnCompleteListener {
                    Log.e("eliminarAlimento", "Error al eliminar ")
                }
        }
    }
}
