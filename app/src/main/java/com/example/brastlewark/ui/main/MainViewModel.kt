package com.example.brastlewark.ui.main

import androidx.lifecycle.*
import com.example.brastlewark.model.Gnome
import com.example.brastlewark.repository.GnomeRepository
import com.example.brastlewark.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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
                is MainStateEvent.None -> {
                    TODO("Nothing to do here.")
                }
            }
        }
    }
}

sealed class MainStateEvent{
    object GetGnomeEvents: MainStateEvent()
    object None: MainStateEvent()
}