package com.tarea2.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.tarea2.data.JuegoDao
import com.tarea2.data.JuegoDataBase
import com.tarea2.model.Juego
import com.tarea2.repository.JuegoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JuegoViewModel(application : Application) : AndroidViewModel(application)  {

    //Atributo para acceder al repositorio de Lugar
    private val repository : JuegoRepository

    //Atributo para obtener la lista de lugares en un ArrayList especial
    val getAllData : LiveData<List<Juego>>

    //Bloque de inicialización de los atributos
    init{
        val JuegoDao = JuegoDataBase.getDataBase(application).JuegoDao()
        repository = JuegoRepository(JuegoDao)
        getAllData = repository.getAllData
    }

    fun addJuego(Juego: Juego){
        viewModelScope.launch (Dispatchers.IO){
            repository.addJuego(Juego)
        }
    }
}