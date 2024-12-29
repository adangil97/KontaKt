package com.example.core

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
abstract class ActionsScreenModel<State>(
    initialState: State
) : StateScreenModel<State>(initialState) {
    // To performs ui actions
    private val mutableActions = MutableSharedFlow<ActionsScreen>()
    val actions = mutableActions.asSharedFlow()

    protected inline fun <ResultType> fetchFromActions(
        crossinline request: suspend (ActionsScreen) -> Flow<List<ResultType>>,
        crossinline onSuccess: suspend (List<ResultType>) -> Unit
    ) {
        screenModelScope.launch {
            actions.flatMapLatest { action ->
                request(action)
            }.collect {
                onSuccess(it)
            }
        }
    }

    fun sendAction(action: ActionsScreen) {
        screenModelScope.launch {
            mutableActions.emit(action)
        }
    }

    fun updateState(newState: State) {
        mutableState.value = newState
    }

    protected fun currentState() = mutableState.value
}

data class ActionsScreen(
    val searchQuery: String = ""
)