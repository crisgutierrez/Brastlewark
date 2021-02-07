package com.example.brastlewark.ui.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brastlewark.R
import com.example.brastlewark.ext.hideInProgress
import com.example.brastlewark.ext.showInProgress
import com.example.brastlewark.model.Gnome
import com.example.brastlewark.ui.adapter.GnomeAdapter
import com.example.brastlewark.ui.viewmodel.MainStateEvent
import com.example.brastlewark.ui.viewmodel.MainViewModel
import com.example.brastlewark.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment() {

    private val navController by lazy { findNavController(this) }
    private val viewModel: MainViewModel by viewModels()
    private var gnomeList = emptyList<Gnome>()


    private val gnomeAdapter = GnomeAdapter(object :
        GnomeAdapter.OnGnomeClickedListener {
        override fun onGnomeClicked(gnomeId: Int) {
            val gnome = gnomeList.find { it.id == gnomeId }
            Log.e("cristian", "onGnomeClicked name: ${gnome?.name}")
            navController.navigate(
                MainFragmentDirections.actionMainFragmentToGnomeDetailsFragment().setGnome(gnome)
            )
        }
    }
    )

    // region LIFECYCLE ----------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_menu_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.setStateEvent(MainStateEvent.FilterEvents(newText, gnomeList))
                }
                return false
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setLayout()

        if (gnomeList.isEmpty()) {
            viewModel.setStateEvent(MainStateEvent.GetGnomeEvents)
        }
    }
    // endregion

    // region PRIVATE METHODS -----------------------------------------------------------------------
    private fun setLayout() {
        gnome_recycler_view.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gnomeAdapter
        }
    }


    private fun setObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataSate ->
            when (dataSate) {
                is DataState.InProgress -> {
                    showInProgress()
                    Log.e("cristian", "InProgress")

                }
                is DataState.Cached -> {
                    hideInProgress()
                    gnomeList = dataSate.data
                    gnomeAdapter.setGnomes(gnomeList)

                }
                is DataState.Success -> {
                    hideInProgress()
                    gnomeList = dataSate.data
                    gnomeAdapter.setGnomes(gnomeList)

                }
                is DataState.FilteredList -> {
                    hideInProgress()
                    gnomeAdapter.setGnomes(dataSate.data)

                }
                is DataState.Failure -> {
                    hideInProgress()
                    Log.e("cristian", "Failure: ${dataSate.exception.message}")

                }
            }
        })
    }
    // endregion

}