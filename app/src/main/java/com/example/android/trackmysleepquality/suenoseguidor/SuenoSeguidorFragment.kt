
package com.example.android.trackmysleepquality.suenoseguidor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.basededatos.SuenoDatabase
import com.example.android.trackmysleepquality.databinding.FragmentSuenoSeguidorBinding


class SuenoSeguidorFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSuenoSeguidorBinding= DataBindingUtil.inflate(
                inflater, R.layout.fragment_sueno_seguidor, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = SuenoDatabase.getInstance(application).suenoDatabaseDAO

        val viewModelFactory = SuenoSeguidorViewModelFactory(dataSource, application)

        val suenoSeguidorViewModel = ViewModelProvider(this, viewModelFactory).get(SuenoSeguidorViewModel::class.java)

        binding.suenoSeguidorViewModel = suenoSeguidorViewModel

        binding.lifecycleOwner = this

        suenoSeguidorViewModel.navegarASuenoCalidad.observe(viewLifecycleOwner, Observer { noche ->

            noche?.let {

                this.findNavController().navigate(
                        SuenoSeguidorFragmentDirections.actionSuenoSeguidorFragmentToSuenoCalidadFragment(noche.nocheId)
                )

                suenoSeguidorViewModel.dejarDeNavegar()

            }

        })


        val adaptador = SuenoNocheAdapter()

        binding.suenoLista.adapter = adaptador

        suenoSeguidorViewModel.noches.observe(viewLifecycleOwner, Observer {

            it.let{

                adaptador.data = it
            }

        })

        return binding.root
    }
}
