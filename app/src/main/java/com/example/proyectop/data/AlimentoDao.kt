package com.example.proyectop.data


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.proyectop.model.Alimento

@Dao
interface AlimentoDao {
    @Query("SELECT * FROM ALIMENTO")
    fun getAlimentos(): LiveData<List<Alimento>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarAlimento(inmobiliaria: Alimento)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun actualizarAlimento(inmobiliaria: Alimento)

    @Delete
    suspend fun eliminarAlimento(inmobiliaria: Alimento)
}
