package org.nkoro.medanalytics.feature.home.mainScreen

import org.nkoro.medanalytics.core.ui.mvi.MviScreenModel
import org.nkoro.medanalytics.feature.home.data.model.AdminKPIModel
import org.nkoro.medanalytics.feature.home.data.model.RingModel
import org.nkoro.medanalytics.feature.home.data.model.RingStatus

class MainScreenModel : MviScreenModel<Effect, Action, State>(
    defaultState = State()
) {
    init {
        pushState {
            it.copy(
                conversionRate = 78.7,
                avgDuration = 163,
                transcriptionAccuracy = 88.0,
                adminsEvaluation = listOf(
                    AdminKPIModel(
                        fio = "Жмых А.В.",
                        evaluation = 95.0,
                        avgDuration = 125,
                        leadPercent = 56.4,
                        accordance = 87.4,
                        improvisation = 23.5,
                    ),
                    AdminKPIModel(
                        fio = "Иванова М.А.",
                        evaluation = 74.5,
                        avgDuration = 135,
                        leadPercent = 58.4,
                        accordance = 84.4,
                        improvisation = 17.2,
                    ),
                    AdminKPIModel(
                        fio = "Светова С.В.",
                        evaluation = 82.0,
                        avgDuration = 137,
                        leadPercent = 66.2,
                        accordance = 88.2,
                        improvisation = 24.6,
                    )
                ),
                rings = listOf(
                    RingModel(
                        dateTime = "14.05.2025 10:24",
                        duration = 146,
                        operator = "Иванова М.А.",
                        patient = "Скотникова Валентина Антоновна",
                        service = "Прием терапевта",
                        doctor = "Волкова А.В.",
                        status = RingStatus.CREATED,
                    ),
                    RingModel(
                        dateTime = "14.05.2025 10:24",
                        duration = 146,
                        operator = "Иванова М.А.",
                        patient = "Скотникова Валентина Антоновна",
                        service = "Прием терапевта",
                        doctor = "Волкова А.В.",
                        status = RingStatus.NOT_RECORDED,
                    ),
                    RingModel(
                        dateTime = "14.05.2025 10:24",
                        duration = 146,
                        operator = "Иванова М.А.",
                        patient = "Скотникова Валентина Антоновна",
                        service = "Прием терапевта",
                        doctor = "Волкова А.В.",
                        status = RingStatus.CREATED,
                    ),
                    RingModel(
                        dateTime = "14.05.2025 10:24",
                        duration = 146,
                        operator = "Иванова М.А.",
                        patient = "Скотникова Валентина Антоновна",
                        service = "Прием терапевта",
                        doctor = "Волкова А.В.",
                        status = RingStatus.CREATED,
                    ),
                    RingModel(
                        dateTime = "14.05.2025 10:24",
                        duration = 146,
                        operator = "Иванова М.А.",
                        patient = "Скотникова Валентина Антоновна",
                        service = "Прием терапевта",
                        doctor = "Волкова А.В.",
                        status = RingStatus.NOT_RECORDED,
                    ),
                    RingModel(
                        dateTime = "14.05.2025 10:24",
                        duration = 146,
                        operator = "Иванова М.А.",
                        patient = "Скотникова Валентина Антоновна",
                        service = "Прием терапевта",
                        doctor = "Волкова А.В.",
                        status = RingStatus.CREATED,
                    ),
                    RingModel(
                        dateTime = "14.05.2025 10:24",
                        duration = 146,
                        operator = "Иванова М.А.",
                        patient = "Скотникова Валентина Антоновна",
                        service = "Прием терапевта",
                        doctor = "Волкова А.В.",
                        status = RingStatus.CREATED,
                    ),
                    RingModel(
                        dateTime = "14.05.2025 10:24",
                        duration = 146,
                        operator = "Иванова М.А.",
                        patient = "Скотникова Валентина Антоновна",
                        service = "Прием терапевта",
                        doctor = "Волкова А.В.",
                        status = RingStatus.NOT_RECORDED,
                    ),
                    RingModel(
                        dateTime = "14.05.2025 10:24",
                        duration = 146,
                        operator = "Иванова М.А.",
                        patient = "Скотникова Валентина Антоновна",
                        service = "Прием терапевта",
                        doctor = "Волкова А.В.",
                        status = RingStatus.CREATED,
                    ),
                ),
            )
        }
    }

    override suspend fun handleAction(action: Action) {
        when (action) {
            Action.Open -> pushState { it.copy(enable = !it.enable) }
        }
    }
}