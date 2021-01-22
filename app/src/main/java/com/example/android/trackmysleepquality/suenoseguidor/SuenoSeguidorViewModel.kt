/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.suenoseguidor

import android.app.Application
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.aplicarFormatoANoches
import com.example.android.trackmysleepquality.basededatos.SuenoDatabaseDAO
import com.example.android.trackmysleepquality.basededatos.SuenoNoche
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * ViewModel for SuenoSeguidorFragment.
 */
class SuenoSeguidorViewModel(
        val database: SuenoDatabaseDAO,
        application: Application) : AndroidViewModel(application) {

    private  var nocheActual = MutableLiveData<SuenoNoche?>()

     val noches = database.obtenerTodasLasNoches()

    val nochesString = Transformations.map(noches) { noches ->
        aplicarFormatoANoches(noches, application.resources)
    }

    val iniciarButtonVisible = Transformations.map(nocheActual){
        null == it
    }

    val terminarButtonVisible = Transformations.map(nocheActual){
        null != it
    }

    val limpiarButtonVisible = Transformations.map(noches){
        it?.isNotEmpty()
    }


    private val _navegarASuenoCalidad = MutableLiveData<SuenoNoche>()

    val navegarASuenoCalidad: LiveData<SuenoNoche> get() = _navegarASuenoCalidad

    fun dejarDeNavegar(){
        _navegarASuenoCalidad.value = null
    }


    init {
        inicializarNocheActual()
    }

    private fun inicializarNocheActual() {

        viewModelScope.launch {

            nocheActual.value = obtenerNocheActualDeDatabase()

        }

    }

    private suspend fun obtenerNocheActualDeDatabase(): SuenoNoche? {
        var noche = database.obtenerUltimaNoche()

        if(noche?.terminoTiempoMilli != noche?.inicioTiempoMilli){
            noche = null

        }
        return noche

    }

    fun iniciarSeguirSueno(){

        viewModelScope.launch {
            val nuevaNoche = SuenoNoche()

            insertar(nuevaNoche)

            nocheActual.value = obtenerNocheActualDeDatabase()
        }

    }

    private suspend fun insertar(noche: SuenoNoche) {

        database.insertar(noche)
    }

    fun detenerSeguirSueno(){

        viewModelScope.launch {
            val nocheAntigua = nocheActual.value ?: return@launch

            nocheAntigua.terminoTiempoMilli = System.currentTimeMillis()

            actualizar(nocheAntigua)

            _navegarASuenoCalidad.value = nocheAntigua

        }

    }

    private suspend fun actualizar(noche: SuenoNoche) {

        database.actualizar(noche)
    }

    fun limpiarSuenos(){

        viewModelScope.launch {
            limpiar()

            nocheActual.value = null
        }
    }

    private suspend fun limpiar() {
        database.limpiar()
    }


}
