package com.example.proyectop.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectop.model.Alimento


@Database(entities = [Alimento::class], version = 1, exportSchema = false)
abstract class AlimentoDatabase() : RoomDatabase(){

abstract fun alimentoDao() : AlimentoDao

companion object{
    @Volatile
    private var INSTANCE:AlimentoDatabase? = null

    fun getDatabase(context: android.content.Context): AlimentoDatabase {
        val temp = INSTANCE
        if (temp != null){
            return temp
        }
        synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AlimentoDatabase::class.java,
                "Alimento_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
}
}
