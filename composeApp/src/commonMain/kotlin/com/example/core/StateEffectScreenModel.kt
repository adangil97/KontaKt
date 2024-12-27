package com.example.core

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class StateEffectScreenModel<State, Effect>(
    initialState: State
) : StateScreenModel<State>(initialState) {

    // To perform ui effects like a snack-bar, toast or similar.
    private val mutableEffect = MutableSharedFlow<Effect?>()
    val effect = mutableEffect.asSharedFlow()

    fun updateState(newState: State) {
        mutableState.value = newState
    }

    protected fun currentState() = mutableState.value

    protected fun launchEffect(effect: Effect) {
        screenModelScope.launch {
            mutableEffect.emit(effect)
        }
    }
}