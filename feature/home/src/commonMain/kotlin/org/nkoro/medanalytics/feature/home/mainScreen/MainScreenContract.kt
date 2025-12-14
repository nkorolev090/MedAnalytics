package org.nkoro.medanalytics.feature.home.mainScreen

import org.nkoro.medanalytics.core.ui.mvi.MviAction
import org.nkoro.medanalytics.core.ui.mvi.MviEffect
import org.nkoro.medanalytics.core.ui.mvi.MviState
import org.nkoro.medanalytics.feature.home.data.model.AdminKPIModel
import org.nkoro.medanalytics.feature.home.data.model.RingModel

sealed interface Effect : MviEffect

sealed interface Action : MviAction {
    data object Open : Action
}

data class State(
    val enable: Boolean = false,
    val conversionRate: Double = 0.0,
    val avgDuration: Int = 0,
    val transcriptionAccuracy: Double = 0.0,
    val adminsEvaluation: List<AdminKPIModel> = emptyList(),
    val rings: List<RingModel> = emptyList(),
) : MviState