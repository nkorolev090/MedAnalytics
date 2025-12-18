package org.nkoro.medanalytics.core.common.files

import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.files.File
import org.w3c.files.FileReader
import org.w3c.files.get
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class FilePicker {
    actual suspend fun pickFile(accept: String): String? {
        return suspendCoroutine { continuation ->
            // Создаем input элемент для выбора файла
            val input = document.createElement("input").unsafeCast<HTMLInputElement>()
            input.type = "file"
            input.accept = accept

            // Обработчик изменения (выбора файла)
            input.onchange = { _ ->
                val file = input.files?.get(0)

                if (file != null) {
                    readFileAsText(file, continuation)
                } else {
                    continuation.resume(null)
                }

                // Удаляем элемент из DOM
                document.body?.removeChild(input)
            }

            // Добавляем элемент в DOM и имитируем клик
            input.style.display = "none"
            document.body?.appendChild(input)
            input.click()
        }
    }

    @OptIn(ExperimentalWasmJsInterop::class)
    private fun readFileAsText(
        file: File,
        continuation: kotlin.coroutines.Continuation<String?>
    ) {
        val reader = FileReader()

        reader.onload = { _ ->
            val result = reader.result.toString()
            continuation.resume(result)
        }

        reader.onerror = { _ ->
            println("Failed to read file: $file.name")
            continuation.resume(null)
        }

        // Читаем файл как текст с UTF-8 кодировкой
        reader.readAsText(file, "UTF-8")
    }
}