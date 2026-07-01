package br.edu.ifsp.hto.htoipdm.filmes

import br.edu.ifsp.hto.htoipdm.filmes.features.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiEventManager @Inject constructor() {

    private val _events = MutableSharedFlow<UiEvent>()

    val events = _events.asSharedFlow()

    suspend fun emit(event: UiEvent) {
        _events.emit(event)
    }
}