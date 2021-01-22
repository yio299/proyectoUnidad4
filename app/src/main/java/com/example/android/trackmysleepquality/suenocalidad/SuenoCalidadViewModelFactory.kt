
package com.example.android.trackmysleepquality.suenocalidad

/**
 * Esto es codigo de relleno para crear un ViewModel Factory.
 *
 * Proporciona SuenoNocheKey y suenoDatabaseDAO a el ViewModel.
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.basededatos.SuenoDatabaseDAO

class SuenoCalidadViewModelFactory(
        private val suenoNocheKey: Long,
        private val dataSource: SuenoDatabaseDAO) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuenoCalidadViewModel::class.java)) {
            return SuenoCalidadViewModel(suenoNocheKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
