package org.nkoro.medanalytics.feature.home.data.model

import androidx.compose.runtime.Immutable
import medanalytics.feature.home.generated.resources.Res
import medanalytics.feature.home.generated.resources.created
import medanalytics.feature.home.generated.resources.no_recorded

@Immutable
data class RingModel(
    val dateTime: String,
    val duration: Int,
    val operator: String,
    val patient: String?,
    val service: String?,
    val doctor: String?,
    val status: RingStatus,
)

enum class RingStatus(val status: String) {
    CREATED("Запись создана"),
    NOT_RECORDED("Не записан");

        companion object{
        fun get(status: String): RingStatus{
            return RingStatus.entries.associateBy{it.status}[status] ?: NOT_RECORDED
        }
    }
}

fun RingStatus.getStringRes() =
    when (this) {
        RingStatus.CREATED -> Res.string.created
        RingStatus.NOT_RECORDED -> Res.string.no_recorded
    }