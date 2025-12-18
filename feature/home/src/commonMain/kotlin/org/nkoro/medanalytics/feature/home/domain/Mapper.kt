package org.nkoro.medanalytics.feature.home.domain

import org.nkoro.medanalytics.feature.home.data.model.RingModel
import org.nkoro.medanalytics.feature.home.data.model.RingStatus
import org.nkoro.medanalytics.feature.home.domain.entity.CallRecord

fun List<CallRecord>.mapToModel(): List<RingModel> =
    this.map { it.mapToModel() }

fun CallRecord.mapToModel()= RingModel(
    dateTime = this.callDateTime,
    duration = this.durationSeconds,
    operator = this.operatorName,
    patient = this.patientName,
    service = this.service,
    doctor = this.doctor,
    status = RingStatus.get(this.status)
)