
package com.example.android.trackmysleepquality.basededatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_calidad_sueno_diaria")
data class SuenoNoche(

        @PrimaryKey(autoGenerate = true)
        var nocheId: Long = 0L,

        @ColumnInfo(name = "inicio_tiempo_milli")
        val inicioTiempoMilli: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "termino_tiempo_milli")
        var terminoTiempoMilli: Long = inicioTiempoMilli,

        @ColumnInfo(name = "calidad_puntuacion")
        var suenoCalidad: Int = -1

)