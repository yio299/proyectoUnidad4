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

package com.example.android.trackmysleepquality.suenocalidad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.basededatos.SuenoDatabase
import com.example.android.trackmysleepquality.databinding.FragmentSuenoCalidadBinding

class SuenoCalidadFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSuenoCalidadBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sueno_calidad, container, false)

        val application = requireNotNull(this.activity).application

        val argumentos = SuenoCalidadFragmentArgs.fromBundle(arguments!!)

        val dataSource = SuenoDatabase.getInstance(application).suenoDatabaseDAO

        val viewModelFactory = SuenoCalidadViewModelFactory(argumentos.sleepNightKey, dataSource)

        val suenoCalidadViewModel = ViewModelProvider(this, viewModelFactory).get(SuenoCalidadViewModel::class.java)

        binding.suenoCalidadVieModel = suenoCalidadViewModel

        suenoCalidadViewModel.navegarASuenoSeguidor.observe(viewLifecycleOwner, Observer {

            if(it == true){

                this.findNavController().navigate(SuenoCalidadFragmentDirections.actionSuenoCalidadFragmentToSuenoSeguidorFragment())

                suenoCalidadViewModel.navegacionTerminada()

            }

        })

        return binding.root
    }
}
