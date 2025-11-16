package org.nkoro.medanalytics.feature.home.mainPage

import org.nkoro.medanalytics.core.ui.mvi.MviAction
import org.nkoro.medanalytics.core.ui.mvi.MviEffect
import org.nkoro.medanalytics.core.ui.mvi.MviState

sealed interface Effect : MviEffect

sealed interface Action : MviAction {
    data object Open : Action
}

data class State(
    val enable: Boolean = false

) : MviState