package com.example.brastlewark.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.brastlewark.R
import com.example.brastlewark.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment() {

    private val navController by lazy { findNavController(this) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        viewModel.setStateEvent(MainStateEvent.GetGnomeEvents)

        buttonId.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_gnomeDetailsFragment) }
    }

    // region PRIVATE METHODS -----------------------------------------------------------------------
    private fun setObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataSate ->
            when (dataSate) {
                is DataState.InProgress -> {
                    Log.e("cristian", "InProgress")

                }
                is DataState.Success -> {
                    val gnomes = dataSate.data
                    Log.e("cristian", "gnomes: $gnomes")

                }
                is DataState.Failure -> {
                    Log.e("cristian", "Failure: ${dataSate.exception.message}")

                }
            }
        })
    }
    // endregion

}