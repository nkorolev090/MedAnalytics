package org.nkoro.medanalytics.feature.home.mainPage

import org.nkoro.medanalytics.core.ui.mvi.MviScreenModel

class MainScreenModel : MviScreenModel<Effect, Action, State>(
    defaultState = State()
) {
    override suspend fun handleAction(action: Action) {
        when (action) {
            Action.Open -> pushState { it.copy(enable = !it.enable) }
        }
    }
}