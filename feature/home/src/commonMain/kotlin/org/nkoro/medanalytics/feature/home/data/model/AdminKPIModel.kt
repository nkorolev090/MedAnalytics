package org.nkoro.medanalytics.feature.home.data.model

import androidx.compose.runtime.Immutable
import medanalytics.feature.home.generated.resources.*
import org.jetbrains.compose.resources.StringResource

@Immutable
data class AdminKPIModel(
    val fio: String,
    val evaluation: Double,
    val avgDuration: Int,
    val leadPercent: Double,
    val accordance: Double,
    val improvisation: Double,
) {
    val evaluationText: StringResource
        get() = when (evaluation) {
            in 80.0..100.0 -> Res.string.excellent
            in 50.0.rangeUntil(80.0) -> Res.string.good
            in 30.0.rangeUntil(50.0) -> Res.string.satisfactory
            else -> Res.string.no_satisfactory
        }
}

