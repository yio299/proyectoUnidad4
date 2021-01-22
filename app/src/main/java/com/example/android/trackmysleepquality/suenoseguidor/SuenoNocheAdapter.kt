package com.example.android.trackmysleepquality.suenoseguidor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.ConvertirLaCalidadDeNumeroAString
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.basededatos.SuenoNoche
import com.example.android.trackmysleepquality.convertirDuracionFormato

class SuenoNocheAdapter: RecyclerView.Adapter<SuenoNocheAdapter.ViewHolder>(){

    var data = listOf<SuenoNoche>()
    set(valor){

        field = valor
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
       val vista = layoutInflater.inflate(R.layout.list_item_sueno_noche, parent, false)

       return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {

        return data.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val suenoDuracion: TextView = itemView.findViewById(R.id.sleep_length)
        val calidad: TextView = itemView.findViewById(R.id.quality_string)
        val calidadImagen: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(item: SuenoNoche) {

            val res = itemView.context.resources

            suenoDuracion.text = convertirDuracionFormato(item.inicioTiempoMilli, item.terminoTiempoMilli, res)

            calidad.text = ConvertirLaCalidadDeNumeroAString(item.suenoCalidad, res)

            calidadImagen.setImageResource(

            when (item.suenoCalidad) {

                0 -> R.drawable.ic_dormir_0__2_
                1 -> R.drawable.ic_dormir_1
                2 -> R.drawable.ic_dormir_2
                3 -> R.drawable.ic_dormir_3
                4 -> R.drawable.ic_dormir_4
                5 -> R.drawable.ic_dormir_5
                else -> R.drawable.ic_dormir_active
            }
            )


        }

    }



}

