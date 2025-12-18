package org.nkoro.medanalytics.feature.home.mainScreen

import org.nkoro.medanalytics.core.ui.mvi.MviScreenModel
import org.nkoro.medanalytics.feature.home.data.model.AdminKPIModel
import org.nkoro.medanalytics.feature.home.data.services.JsonFileLoader
import org.nkoro.medanalytics.feature.home.domain.mapToModel

class MainScreenModel : MviScreenModel<Effect, Action, State>(
    defaultState = State()
) {

    override suspend fun handleAction(action: Action) {
        when (action) {
            Action.LoadFile -> loadFile()
        }
    }

    private suspend fun loadFile() {
        val filePicker = org.nkoro.medanalytics.core.common.files.FilePicker()
        val jsonLoader = JsonFileLoader(filePicker)
        val records = jsonLoader.loadCallRecords()

        pushState { previewState.copy(rings = records.mapToModel()) }
    }
}

private val previewState = State(
    conversionRate = 78.7,
    avgDuration = 163,
    transcriptionAccuracy = 88.0,
    adminsEvaluation = listOf(
        AdminKPIModel(
            fio = "Иванова Анна Сергеевна",
            evaluation = 95.0,
            avgDuration = 125,
            leadPercent = 56.4,
            accordance = 87.4,
            improvisation = 23.5,
        ),
        AdminKPIModel(
            fio = "Cмирнов Алексей Дмитриевич",
            evaluation = 74.5,
            avgDuration = 135,
            leadPercent = 58.4,
            accordance = 84.4,
            improvisation = 17.2,
        ),
        AdminKPIModel(
            fio = "Петрова Мария Ивановна",
            evaluation = 82.0,
            avgDuration = 137,
            leadPercent = 66.2,
            accordance = 88.2,
            improvisation = 24.6,
        )
    ),
)