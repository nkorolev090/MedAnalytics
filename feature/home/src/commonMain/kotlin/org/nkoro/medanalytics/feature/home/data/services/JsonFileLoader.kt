package org.nkoro.medanalytics.feature.home.data.services
import kotlinx.serialization.json.Json
import org.nkoro.medanalytics.core.common.files.FilePicker
import org.nkoro.medanalytics.feature.home.domain.entity.CallRecord

class JsonFileLoader(private val filePicker: FilePicker) {
    
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    
//    suspend fun <T> loadAndParseJsonFile(type: Class<T>): T? {
//        val jsonContent = filePicker.pickFile(".json")
//
//        return jsonContent?.let { content ->
//            try {
//                // Для простоты возвращаем строку, в реальном случае используйте kotlinx.serialization
//                content as? T
//            } catch (e: Exception) {
//                println("Error parsing JSON: ${e.message}")
//                null
//            }
//        }
//    }
    
    suspend fun loadCallRecords(): List<CallRecord> {
        val jsonContent = filePicker.pickFile(".json")
        
        return if (jsonContent != null) {
            try {
                json.decodeFromString<List<CallRecord>>(jsonContent)
            } catch (e: Exception) {
                println("Error parsing call records: ${e.message}")
                emptyList()
            }
        } else {
            emptyList()
        }
    }
}