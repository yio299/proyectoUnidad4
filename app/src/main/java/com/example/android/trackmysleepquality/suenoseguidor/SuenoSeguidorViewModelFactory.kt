
package com.example.android.trackmysleepquality.suenoseguidor

/**
 * Esto es codigo de relleno para crear un ViewModel Factory.
 *
 * Proporciona SuenoDatabaseDAO y context a el ViewModel.
 */

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.basededatos.SuenoDatabaseDAO

class SuenoSeguidorViewModelFactory(
        private val dataSource: SuenoDatabaseDAO,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuenoSeguidorViewModel::class.java)) {
            return SuenoSeguidorViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

