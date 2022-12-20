package com.tarea2.repository

import androidx.lifecycle.LiveData
import com.tarea2.data.JuegoDao
import com.tarea2.model.Juego

class JuegoRepository (private val JuegoDao: JuegoDao){

    suspend fun addJuego(Juego : Juego){
        JuegoDao.addJuego(Juego)
    }

    suspend fun updateJuego(Juego : Juego) {
        JuegoDao.addJuego(Juego)
    }
    suspend fun deleteJuego(Juego : Juego) {
        JuegoDao.addJuego(Juego)
    }

    val getAllData : LiveData<List<Juego>> = JuegoDao.getAllData()
}