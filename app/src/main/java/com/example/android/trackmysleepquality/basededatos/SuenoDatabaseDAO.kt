
package com.example.android.trackmysleepquality.basededatos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SuenoDatabaseDAO {

    @Insert
    suspend fun insertar(noche: SuenoNoche)

    @Update
    suspend fun actualizar(noche: SuenoNoche)

    @Query("SELECT * FROM tabla_calidad_sueno_diaria WHERE nocheId = :key")
    suspend fun obtener(key: Long): SuenoNoche?

    @Query("DELETE FROM tabla_calidad_sueno_diaria")
    suspend fun limpiar()

    @Query("SELECT * FROM tabla_calidad_sueno_diaria ORDER BY nocheId DESC ")
    fun obtenerTodasLasNoches(): LiveData<List<SuenoNoche>>

    @Query("SELECT * FROM tabla_calidad_sueno_diaria ORDER BY nocheId DESC LIMIT 1")
    suspend fun obtenerUltimaNoche(): SuenoNoche?

}