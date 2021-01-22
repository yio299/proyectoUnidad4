package com.example.android.trackmysleepquality

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.basededatos.SuenoNoche
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private val UN_MINUTO_MILLIS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES)
private val UNA_HORA_MILLIS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

fun convertirDuracionFormato(startTimeMilli: Long, endTimeMilli: Long, res: Resources): String {
    val durationMilli = endTimeMilli - startTimeMilli
    val weekdayString = SimpleDateFormat("EEEE", Locale.getDefault()).format(startTimeMilli)
    return when {
        durationMilli < UN_MINUTO_MILLIS -> {
            val seconds = TimeUnit.SECONDS.convert(durationMilli, TimeUnit.MILLISECONDS)
            res.getString(R.string.segundos_length, seconds, weekdayString)
        }
        durationMilli < UNA_HORA_MILLIS -> {
            val minutes = TimeUnit.MINUTES.convert(durationMilli, TimeUnit.MILLISECONDS)
            res.getString(R.string.minutos_length, minutes, weekdayString)
        }
        else -> {
            val hours = TimeUnit.HOURS.convert(durationMilli, TimeUnit.MILLISECONDS)
            res.getString(R.string.horas_length, hours, weekdayString)
        }
    }
}

fun ConvertirLaCalidadDeNumeroAString(calidad: Int, recursos: Resources): String {
    var calidadString = recursos.getString(R.string.tres_bien)
    when (calidad) {
        -1 -> calidadString = "--"
        0 -> calidadString = recursos.getString(R.string.cero_muy_mal)
        1 -> calidadString = recursos.getString(R.string.uno_pobre)
        2 -> calidadString = recursos.getString(R.string.dos_mas_o_menos)
        4 -> calidadString = recursos.getString(R.string.cuatro_muy_bien)
        5 -> calidadString = recursos.getString(R.string.cinco_exelente)
    }
    return calidadString
}

// ConvertirLongADateString
@SuppressLint("SimpleDateFormat")
fun ConvertirLongADateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
            .format(systemTime).toString()
}


fun aplicarFormatoANoches(nights: List<SuenoNoche>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.titulo))
        nights.forEach {
            append("<br>")
            append(resources.getString(R.string.iniciar_tiempo))
            append("\t${ConvertirLongADateString(it.inicioTiempoMilli)}<br>")
            if (it.terminoTiempoMilli != it.inicioTiempoMilli) {
                append(resources.getString(R.string.terminar_tiempo))
                append("\t${ConvertirLongADateString(it.terminoTiempoMilli)}<br>")
                append(resources.getString(R.string.calidad))
                append("\t${ConvertirLaCalidadDeNumeroAString(it.suenoCalidad, resources)}<br>")
                append(resources.getString(R.string.horas_dormidas))
                // Hours
                append("\t ${it.terminoTiempoMilli.minus(it.inicioTiempoMilli) / 1000 / 60 / 60}:")
                // Minutes
                append("${it.terminoTiempoMilli.minus(it.inicioTiempoMilli) / 1000 / 60}:")
                // Seconds
                append("${it.terminoTiempoMilli.minus(it.inicioTiempoMilli) / 1000}<br><br>")
            }
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)


