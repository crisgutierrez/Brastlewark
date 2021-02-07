package com.example.brastlewark.ui.viewmodel

import androidx.lifecycle.*
import com.example.brastlewark.model.Gnome
import com.example.brastlewark.repository.GnomeRepository
import com.example.brastlewark.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: GnomeRepository
    ) : ViewModel(){
    private val _dataState: MutableLiveData<DataState<List<Gnome>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Gnome>>> = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetGnomeEvents -> {
                    repository.getGnomes().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.FilterEvents -> {
                    val query = mainStateEvent.query.toLowerCase(Locale.getDefault())
                    if (query.isNotEmpty()) {
                        val filteredList = mainStateEvent.gnomeList.filter { gnome ->
                            gnome.name.toLowerCase(Locale.getDefault()).contains(query) || gnome.professions.any {
                                it.toLowerCase(Locale.getDefault()).contains(
                                    query
                                )
                            }
                        }
                        _dataState.value = DataState.FilteredList(filteredList)
                    } else {
                        _dataState.value = DataState.FilteredList(mainStateEvent.gnomeList)
                    }
                }
            }
        }
    }
}

sealed class MainStateEvent{
    object GetGnomeEvents: MainStateEvent()
    class FilterEvents(val query: String, val gnomeList: List<Gnome>) : MainStateEvent()
}