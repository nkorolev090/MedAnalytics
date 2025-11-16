package org.nkoro.medanalytics

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform