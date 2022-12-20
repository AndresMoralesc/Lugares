package com.tarea2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tarea2.model.Juego

@Dao
interface JuegoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addJuego(Juego: Juego)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateJuego(Juego: Juego)

    @Delete
    suspend fun deleteJuego(Juego: Juego)

    @Query ("SELECT * FROM Juego")
    fun getAllData() : LiveData<List<Juego>>
}