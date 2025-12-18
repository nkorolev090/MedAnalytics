package org.nkoro.medanalytics.core.common.files

expect class FilePicker(){
    suspend fun pickFile(accept: String = "*/*"): String?
}