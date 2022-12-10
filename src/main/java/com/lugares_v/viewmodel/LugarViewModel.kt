package com.lugares_v.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.lugares_v.data.LugarDao
import com.lugares_v.model.Lugar
import com.lugares_v.repository.LugarRepository
import kotlinx.coroutines.launch
class LugarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LugarRepository = LugarRepository(LugarDao())
    val getLugares= repository.getLugares

    fun saveLugar(lugar: Lugar) {
        viewModelScope.launch { repository.saveLugar(lugar) }
    }
    fun deleteLugar(lugar: Lugar) {
        viewModelScope.launch { repository.deleteLugar(lugar)}
    }
}
