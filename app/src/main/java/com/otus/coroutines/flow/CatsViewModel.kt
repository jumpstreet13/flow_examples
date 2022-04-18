package com.otus.coroutines.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class CatsViewModel : ViewModel() {

    private val _liveData = MutableLiveData<Int>()
    val liveData: LiveData<Int> = _liveData

    /**
     * Shared flow
     *
     *
     * state flow:
     * ignores duplicated
     * needs initial value
     * have state
     * no replay > 1
     */
    private val _uiState = MutableStateFlow<State>(Initial)
    val uiState: StateFlow<State> = _uiState

    private val _uiState2 = MutableSharedFlow<State>()
    val uiState2: SharedFlow<State> = _uiState2

    fun show() {
        viewModelScope.launch {

            val coldFlow = flowOf(1, 2, 3, 4)
                .stateIn(
                    scope = viewModelScope,
                    started = WhileSubscribed(5000),
                    initialValue = 10
                )
        }

        viewModelScope.launch {
            uiState.collect {

            }
        }

    }

    sealed interface State
    object Initial : State
    data class Succes(val items: List<String>) : State
    object Error : State
}