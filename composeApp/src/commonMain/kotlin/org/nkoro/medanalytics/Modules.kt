package org.nkoro.medanalytics

import org.nkoro.medanalytics.feature.home.di.homeScreensModule

private val featureModules
    get() = listOf(
        homeScreensModule,
    )
val appModules
    get() = listOf(
        featureModules,
    ).flatten()