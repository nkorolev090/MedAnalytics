package org.nkoro.medanalytics.feature.home.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CallRecord(
    @SerialName("call_datetime")
    val callDateTime: String,
    
    @SerialName("duration_seconds")
    val durationSeconds: Int,
    
    @SerialName("operator_name")
    val operatorName: String,
    
    @SerialName("patient_name")
    val patientName: String? = null,
    
    @SerialName("service")
    val service: String? = null,
    
    @SerialName("doctor")
    val doctor: String? = null,
    
    @SerialName("status")
    val status: String
)

@Serializable
data class CallRecordsResponse(
    val records: List<CallRecord>
)