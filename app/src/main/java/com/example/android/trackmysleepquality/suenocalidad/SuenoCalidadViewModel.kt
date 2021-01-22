
package com.example.android.trackmysleepquality.suenocalidad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.trackmysleepquality.basededatos.SuenoDatabaseDAO
import kotlinx.coroutines.launch

class SuenoCalidadViewModel(private val suenoNocheKey: Long = 0L, val database: SuenoDatabaseDAO) : ViewModel(){

    private val _navegarASuenoSeguidor = MutableLiveData<Boolean?>()

    val navegarASuenoSeguidor: LiveData<Boolean?> get() = _navegarASuenoSeguidor

    fun navegacionTerminada(){

        _navegarASuenoSeguidor.value = null
    }

    fun cambiarSuenoCalidad(calidad: Int){

        viewModelScope.launch {

            val nocheActual = database.obtener(suenoNocheKey) ?:  return@launch

            nocheActual.suenoCalidad = calidad

            database.actualizar(nocheActual)

            _navegarASuenoSeguidor.value = true

        }

    }

}